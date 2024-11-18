//package CNPM.G14.ems.mapper;
//
//import CNPM.G14.ems.dto.request.AccountCreationRequest;
//import CNPM.G14.ems.dto.request.AccountUpdateRequest;
//import CNPM.G14.ems.dto.response.AccountResponse;
//import CNPM.G14.ems.entity.Account;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Component;
//
//@Component
//@Primary
//public class AccountMapperImplement implements AccountMapper {
//
//    @Override
//    public Account toAccount(AccountCreationRequest request) {
//        if ( request == null ) {
//            return null;
//        }
//
//        Account account = new Account();
//
//        account.setUsername( request.getUsername() );
//        account.setPassword( request.getPassword() );
//
//        return account;
//    }
//
//    @Override
//    public AccountResponse toAccountResponse(Account account) {
//        if ( account == null ) {
//            return null;
//        }
//
//        AccountResponse.AccountResponseBuilder accountResponse = AccountResponse.builder();
//
//        accountResponse.id( account.getId() );
//        accountResponse.username( account.getUsername() );
//
//        return accountResponse.build();
//    }
//
//    @Override
//    public void updateAccount(Account account, AccountUpdateRequest request) {
//        if ( request == null ) {
//            return;
//        }
//        account.setPassword( request.getPassword() );
//    }
//}
