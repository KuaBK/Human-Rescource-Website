package CNPM.G14.ems.service;

import CNPM.G14.ems.dto.request.AccountCreationRequest;
import CNPM.G14.ems.dto.request.AccountUpdateRequest;
import CNPM.G14.ems.dto.response.AccountResponse;

import java.util.List;


public interface AccountService {

    AccountResponse createAccount(AccountCreationRequest request);
    AccountResponse updateAccount(String accountID, AccountUpdateRequest request);
    void deleteAccount(String accountID);

    List<AccountResponse> getAllAccount();
    AccountResponse getAccount(String accountID);
    AccountResponse getInfo(String token);
}
