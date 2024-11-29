package com.Phong.BackEnd.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Phong.BackEnd.dto.request.Account.AccountCreationRequest;
import com.Phong.BackEnd.dto.request.Account.AccountUpdateRequest;
import com.Phong.BackEnd.dto.request.TokenRequest;
import com.Phong.BackEnd.dto.response.AccountResponse;
import com.Phong.BackEnd.dto.response.ApiResponse;
import com.Phong.BackEnd.service.AccountService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountController {
    AccountService accountService;

    @PostMapping("/create")
    ApiResponse<AccountResponse> createUser(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .message("Create Account Successfully")
                .result(accountService.createUser(request))
                .build();
    }

    @GetMapping("/getAll")
    ApiResponse<List<AccountResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<AccountResponse>>builder()
                .result(accountService.getAllUsers())
                .build();
    }

    @GetMapping()
    ApiResponse<AccountResponse> getUser(@RequestParam String id) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getUser(id))
                .build();
    }

    @PostMapping("/myInfo")
    public ApiResponse<AccountResponse> getMyInfo(@RequestBody TokenRequest Request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getMyInfo(Request.getToken()))
                .build();
    }

    @DeleteMapping("/delete")
    ApiResponse<String> deleteUser(@RequestParam String userId) {
        accountService.deleteUser(userId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

    @PutMapping()
    ApiResponse<AccountResponse> updateUser(@RequestParam String id, @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .message("Update Account Successfully")
                .result(accountService.updateUser(id, request))
                .build();
    }
}
