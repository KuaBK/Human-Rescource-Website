//package CNPM.G14.ems.mapper;
//
//import CNPM.G14.ems.dto.request.AccountCreationRequest;
//import CNPM.G14.ems.dto.request.AccountUpdateRequest;
//import CNPM.G14.ems.dto.response.AccountResponse;
//import CNPM.G14.ems.entity.Account;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.MappingTarget;
//
//@Mapper(componentModel = "spring")
//public interface AccountMapper {
//
//    Account toAccount(AccountCreationRequest request);
//
//    AccountResponse toAccountResponse(Account account);
//
//    @Mapping(target = "role", ignore = true)
//    void updateAccount(@MappingTarget Account account, AccountUpdateRequest request);
//}
