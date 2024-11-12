package com.Phong.identityservice.controller;

import java.util.List;
import java.util.Map;

import com.Phong.identityservice.dto.request.TokenRequest;
import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Phong.identityservice.dto.request.ApiResponse;
import com.Phong.identityservice.dto.request.UserCreationRequest;
import com.Phong.identityservice.dto.request.UserUpdateRequest;
import com.Phong.identityservice.dto.response.AccountResponse;
import com.Phong.identityservice.service.AccountService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AccountController {
    AccountService accountService;

    @PostMapping
    ApiResponse<AccountResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<AccountResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<AccountResponse>>builder()
                .result(accountService.getAllUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<AccountResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getUser(userId))
                .build();
    }

    @PostMapping("/myInfo")  // Nhận token từ request body
    public ApiResponse<AccountResponse> getMyInfo(@RequestBody TokenRequest Request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getMyInfo(Request.getToken()))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        accountService.deleteUser(userId);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

    @PutMapping("/{userId}")
    ApiResponse<AccountResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.updateUser(userId, request))
                .build();
    }
}
