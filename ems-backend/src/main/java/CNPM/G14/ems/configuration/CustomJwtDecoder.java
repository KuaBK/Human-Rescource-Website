package CNPM.G14.ems.configuration;
import CNPM.G14.ems.dto.request.IntrospectRequest;
import CNPM.G14.ems.service.impl.AuthenticationServiceImpl;

import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;
import java.text.ParseException;

import com.nimbusds.jose.JOSEException;

@Component
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.signerKey}")
    private String signerKey;

    private final AuthenticationServiceImpl authenticationServiceImpl;

    // constructor
    public CustomJwtDecoder(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    // JWT decoder, used for decoding and verifying JWTs.
    // Initialized as null, and an instance will only be created when needed.
    private NimbusJwtDecoder nimbusJwtDecoder = null;

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var response = authenticationServiceImpl.introspect(
                    IntrospectRequest.builder().token(token).build());

            if (!response.isValid()) throw new JwtException("Token invalid");

        } catch (JOSEException | ParseException e) {
            throw new JwtException(e.getMessage());
        }

        if (Objects.isNull(nimbusJwtDecoder)) {
            // Converts the signerKey into a secret key using the HMAC-SHA512 (HS512) algorithm.
            SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }

        return nimbusJwtDecoder.decode(token);
    }
}
