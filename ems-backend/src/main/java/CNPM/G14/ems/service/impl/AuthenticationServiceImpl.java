package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.request.AuthenticationRequest;
import CNPM.G14.ems.dto.request.IntrospectRequest;
import CNPM.G14.ems.dto.request.LogOutRequest;
import CNPM.G14.ems.dto.request.RefreshRequest;
import CNPM.G14.ems.dto.response.AuthenticationResponse;
import CNPM.G14.ems.dto.response.IntrospectResponse;

import CNPM.G14.ems.entity.Account;
import CNPM.G14.ems.entity.InvalidatedToken;

import CNPM.G14.ems.exception.AppException;
import CNPM.G14.ems.exception.ErrorCode;

import CNPM.G14.ems.repository.AccountRepository;
import CNPM.G14.ems.repository.InvalidatedTokenRepository;

import CNPM.G14.ems.service.AuthenticationService;
import com.nimbusds.jose.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {

    AccountRepository accountRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String signerKey;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long validDuration;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long refreshableDuration;

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var accounts = accountRepository.findByUsername(request.getUsername());
        if (accounts.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        var matchingAccount = accounts.stream()
                .filter(account -> Objects.equals(account.getPassword(), request.getPassword()))
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(matchingAccount);
        var id = matchingAccount.getId();

        return AuthenticationResponse.builder().
                token(token.token).
                expired(token.expired).
                accountId(id).
                authenticated(true)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        // Verify the token
        var signJWT = verifyToken(request.getToken(), true);
        var jit = signJWT.getJWTClaimsSet().getJWTID();
        var expirationTime = signJWT.getJWTClaimsSet().getExpirationTime();

        // Save the invalidated token
        InvalidatedToken invalidatedToken = InvalidatedToken.builder().
                                                tokenID(jit).
                                                expiryTime(expirationTime).
                                                build();
        invalidatedTokenRepository.save(invalidatedToken);

        var accountId = signJWT.getJWTClaimsSet().getStringClaim("accountId");
        var user = accountRepository.findById(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(user);

        return AuthenticationResponse.builder()
                        .accountId(user.getId())
                        .token(token.token)
                        .expired(token.expired)
                        .authenticated(true)
                        .build();
    }

    private TokenInfo generateToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        Date issueTime = new Date();
        Date expiryTime = new Date(Instant.ofEpochMilli(issueTime.getTime())
                .plus(1, ChronoUnit.HOURS)
                .toEpochMilli());

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issuer("EMS")
                .issueTime(issueTime)
                .expirationTime(expiryTime)
                .jwtID(UUID.randomUUID().toString())
                .claim("accountId", account.getId())
                .claim("scope", buildScope(account))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signerKey.getBytes()));
            return new TokenInfo(jwsObject.serialize(), expiryTime);
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void logOut(LogOutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);
            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expirationTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .tokenID(jit)
                    .expiryTime(expirationTime)
                    .build();

            invalidatedTokenRepository.save(invalidatedToken);

        } catch (AppException e) {
            log.info("Token already expired");
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws ParseException, JOSEException {
        JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

        // Parses the token  into a SignedJWT object.
        // Which allows the function to access the token’s header, payload, and signature separately.
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                .getJWTClaimsSet()
                .getIssueTime()
                .toInstant()
                .plus(refreshableDuration, ChronoUnit.SECONDS)
                .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }

    private String buildScope(Account account) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (account.getRole() != null) {
            stringJoiner.add("ROLE_" + account.getRole());
        }
        return stringJoiner.toString();
    }

    private record TokenInfo(String token, Date expired){}
}
