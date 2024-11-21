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
    Account toUser(AccountCreationRequest request);

    AccountResponse toUserResponse(Account account);

    @Mapping(target = "role", ignore = true)
    void updateUser(@MappingTarget Account account, AccountUpdateRequest request);
}
