package com.Phong.BackEnd.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Phong.BackEnd.dto.request.Account.AccountCreationRequest;
import com.Phong.BackEnd.dto.request.Account.AccountUpdateRequest;
import com.Phong.BackEnd.dto.response.AccountResponse;
import com.Phong.BackEnd.entity.Account;
import com.Phong.BackEnd.entity.Role;
import com.Phong.BackEnd.exception.AppException;
import com.Phong.BackEnd.exception.ErrorCode;
import com.Phong.BackEnd.mapper.AccountMapper;
import com.Phong.BackEnd.repository.AccountRepository;
import com.Phong.BackEnd.utils.JwtUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;
    PasswordEncoder passwordEncoder;

    JwtUtils jwtUtils;

    public AccountResponse createUser(AccountCreationRequest request) {

        Account account = accountMapper.toUser(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        account.setRole(Role.PERSONEL);

        try {
            account = accountRepository.save(account);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return accountMapper.toUserResponse(account);
    }

    public AccountResponse updateUser(String userId, AccountUpdateRequest request) {
        Account account =
                accountRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        accountMapper.updateUser(account, request);

        account.setPassword(passwordEncoder.encode(request.getPassword()));

        return accountMapper.toUserResponse(accountRepository.save(account));
    }

    public void deleteUser(String userId) {
        accountRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getAllUsers() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toUserResponse)
                .toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public AccountResponse getUser(String id) {
        return accountMapper.toUserResponse(
                accountRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public AccountResponse getMyInfo(String token) {
        String username = jwtUtils.getUsernameFromToken(token);

        Account account = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return accountMapper.toUserResponse(account);
    }
}
