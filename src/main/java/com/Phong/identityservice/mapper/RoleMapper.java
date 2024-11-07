package com.Phong.identityservice.mapper;

import org.mapstruct.Mapper;

import com.Phong.identityservice.dto.request.RoleRequest;
import com.Phong.identityservice.dto.response.RoleResponse;
import com.Phong.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
