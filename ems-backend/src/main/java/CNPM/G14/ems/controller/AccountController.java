package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.request.*;
import CNPM.G14.ems.dto.response.*;
import CNPM.G14.ems.exception.AppException;
import CNPM.G14.ems.service.AccountService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @PostMapping("/create")
    public ApiResponse<AccountResponse> createAccount(@RequestBody AccountCreationRequest request){
        try {
            AccountResponse result = accountService.createAccount(request);
            return ApiResponse.<AccountResponse>builder()
                    .EC(0)
                    .EM("Create new account succeed")
                    .data(result)
                    .build();
        } catch (Exception e){
            return ApiResponse.<AccountResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/all")
    public ApiResponse<List<AccountResponse>> getAllAccounts(){
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("Authenticated user: {}", authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
//
//        boolean isAdmin = authentication.getAuthorities().stream()
//                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));

        try {
            List<AccountResponse> accountList = accountService.getAllAccount();
            return ApiResponse.<List<AccountResponse>>builder()
                    .EC(0)
                    .EM("Fetch all accounts success")
                    .data(accountList)
                    .build();
        } catch (Exception e){
            return ApiResponse.<List<AccountResponse>>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping
    public ApiResponse<AccountResponse> getAccount(@RequestParam String accountId){
        try {
            AccountResponse account = accountService.getAccount(accountId);
            return ApiResponse.<AccountResponse>builder()
                    .EC(0)
                    .EM("Fetch account data success")
                    .data(account)
                    .build();
        } catch (Exception e){
            return ApiResponse.<AccountResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @GetMapping("/info")
    public ApiResponse<AccountResponse> getInfo(@RequestBody TokenRequest request) {
        try{
            AccountResponse account = accountService.getInfo(request.getToken());
            return ApiResponse.<AccountResponse>builder()
                    .EC(0)
                    .EM("Fetch account data success")
                    .data(account)
                    .build();
        } catch (AppException e){
            return ApiResponse.<AccountResponse>builder()
                    .EC(-1)
                    .EM("Invalidate Token")
                    .data(null)
                    .build();
        }
    }

    @DeleteMapping("/delete")
    public ApiResponse<String> deleteAccount(@RequestParam String accountId) {
        try {
            accountService.deleteAccount(accountId);
            return ApiResponse.<String>builder()
                    .EC(0)
                    .EM("Account has been deleted")
                    .data("Account with id: " + accountId + " has been deleted")
                    .build();
        } catch (AppException e) {
            return ApiResponse.<String>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data("Not found account with id " + accountId)
                    .build();
        }
    }

    @PutMapping("/edit")
    ApiResponse<AccountResponse> updateAccount(@RequestBody AccountUpdateRequest request) {
        try {
            AccountResponse account = accountService.updateAccount(request.getAccountId(), request);
            return ApiResponse.<AccountResponse>builder()
                    .EC(0)
                    .EM("Update account id: " + request.getAccountId() + " succeed")
                    .data(account)
                    .build();
        } catch (AppException e) {
            return ApiResponse.<AccountResponse>builder()
                    .EC(-1)
                    .EM("Not found account with id " + request.getAccountId())
                    .data(null)
                    .build();
        }
    }

}
