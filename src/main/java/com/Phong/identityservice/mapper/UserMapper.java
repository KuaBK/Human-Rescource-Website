package com.Phong.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.Phong.identityservice.dto.request.UserCreationRequest;
import com.Phong.identityservice.dto.request.UserUpdateRequest;
import com.Phong.identityservice.dto.response.UserResponse;
import com.Phong.identityservice.entity.Account;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Account toUser(UserCreationRequest request);

    UserResponse toUserResponse(Account account);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget Account account, UserUpdateRequest request);
}
