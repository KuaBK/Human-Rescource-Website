package com.Phong.identityservice.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Phong.identityservice.constant.PreDefinedRole;
import com.Phong.identityservice.dto.request.UserCreationRequest;
import com.Phong.identityservice.dto.request.UserUpdateRequest;
import com.Phong.identityservice.dto.response.UserResponse;
import com.Phong.identityservice.entity.Account;
import com.Phong.identityservice.entity.Role;
import com.Phong.identityservice.exception.AppException;
import com.Phong.identityservice.exception.ErrorCode;
import com.Phong.identityservice.mapper.UserMapper;
import com.Phong.identityservice.repository.RoleRepository;
import com.Phong.identityservice.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {

        Account account = userMapper.toUser(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PreDefinedRole.PERSONEL_ROLE).ifPresent(roles::add);

        account.setRoles(roles);

        try {
            account = userRepository.save(account);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(account);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        Account account =
                userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(account, request);

        account.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        account.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(account));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    //    @PreAuthorize("hasAuthority('REJECT_POST')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Account account =
                userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(account);
    }
}
