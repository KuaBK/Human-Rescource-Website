package com.Phong.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Phong.identityservice.dto.request.Account.AccountCreationRequest;
import com.Phong.identityservice.dto.request.Account.AccountUpdateRequest;
import com.Phong.identityservice.dto.response.AccountResponse;
import com.Phong.identityservice.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toUser(AccountCreationRequest request);

    AccountResponse toUserResponse(Account account);

    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget Account account, AccountUpdateRequest request);
}
