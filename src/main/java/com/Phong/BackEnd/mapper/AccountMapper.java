package com.Phong.BackEnd.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Phong.BackEnd.dto.request.Account.AccountCreationRequest;
import com.Phong.BackEnd.dto.request.Account.AccountUpdateRequest;
import com.Phong.BackEnd.dto.response.AccountResponse;
import com.Phong.BackEnd.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "password", source = "password")
    Account toUser(AccountCreationRequest request);

    @Mapping(target = "password", source = "password")
    AccountResponse toUserResponse(Account account);

    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget Account account, AccountUpdateRequest request);
}
