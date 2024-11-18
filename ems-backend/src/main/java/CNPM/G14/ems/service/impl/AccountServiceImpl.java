package CNPM.G14.ems.service.impl;

import CNPM.G14.ems.dto.request.AccountCreationRequest;
import CNPM.G14.ems.dto.request.AccountUpdateRequest;
import CNPM.G14.ems.dto.response.AccountResponse;
import CNPM.G14.ems.entity.Account;
import CNPM.G14.ems.exception.AppException;
import CNPM.G14.ems.exception.ErrorCode;
import CNPM.G14.ems.repository.AccountRepository;
import CNPM.G14.ems.service.AccountService;

import CNPM.G14.ems.utils.JwtUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;
    JwtUtils jwtUtils;

    private Account toAccount(AccountCreationRequest request) {
        if ( request == null ) { return null; }

        Account account = new Account();
        account.setUsername( request.getUsername() );
        account.setPassword( request.getPassword() );
        return account;
    }

    private AccountResponse toAccountResponse(Account account) {
        if ( account == null ) { return null; }

        return AccountResponse.builder()
                .id(account.getId())
                .username(account.getUsername())
                .password(account.getPassword())
                .build();
    }

    @Override
    public AccountResponse createAccount(AccountCreationRequest request) {
        Account account = toAccount(request);
        account.setRole("PERSONNEL");
        return toAccountResponse(accountRepository.save(account));
    }

    @Override
    public AccountResponse updateAccount(String accountID, AccountUpdateRequest request){
        Account account = accountRepository.findAccountById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        account.setUsername( request.getUsername() );
        account.setPassword( request.getPassword() );
        return toAccountResponse(accountRepository.save(account));
    }

    @Override
    public void deleteAccount(String accountID){
        Optional<Account> account = accountRepository.findAccountById(accountID);
        if(account.isEmpty()){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        accountRepository.deleteById(accountID);
    }

    @Override
    // @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getAllAccount() {
        return accountRepository.findAll()
                .stream()
                .map(this::toAccountResponse) // Use the internal method for mapping
                .toList();
    }

    @Override
    //@PostAuthorize("returnObject.username == authentication.name")
    public AccountResponse getAccount(String accountID) {
        Account account = accountRepository.findAccountById(accountID)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return toAccountResponse(account);
    }

    public AccountResponse getInfo(String token){
        String accountId = jwtUtils.getIDFromToken(token);
        Account account = accountRepository.findByUsername(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return toAccountResponse(account);
    }

}
