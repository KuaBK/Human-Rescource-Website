package com.Phong.BackEnd.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Phong.BackEnd.entity.Account;
import com.Phong.BackEnd.entity.Role;
import com.Phong.BackEnd.repository.AccountRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

// @Configuration
// @RequiredArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
// @Slf4j
// public class ApplicationInitConfig {
//
//    PasswordEncoder passwordEncoder;
//
//    @NonFinal
//    static final String ADMIN_USER_NAME = "admin";
//
//    @NonFinal
//    static final String ADMIN_PASSWORD = "admin";
//
//    @Bean
//    @ConditionalOnProperty(
//            prefix = "spring",
//            value = "datasource.driverClassName",
//            havingValue = "com.mysql.cj.jdbc.Driver")
//    ApplicationRunner applicationRunner(AccountRepository accountRepository, RoleRepository roleRepository) {
//        return args -> {
//            if (accountRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
//                Object predefinedRole = null;
//                roleRepository.save(
//                        Role.builder().name(PreDefinedRole.PERSONEL_ROLE).build());
//                Role adminRole = roleRepository.save(
//                        Role.builder().name(PreDefinedRole.ADMIN_ROLE).build());
//
//                var roles = new Role();
//                roles.add(adminRole);
//
//                Account account = Account.builder()
//                        .username(ADMIN_USER_NAME)
//                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
//                        .role(roles)
//                        .build();
//
//                accountRepository.save(account);
//                log.warn("admin user has been created with default password: admin, please change it");
//            }
//            log.info("Application initialization completed .....");
//        };
//    }
// }

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    AccountRepository accountRepository;

    static final String ADMIN_USER_NAME = "admin";
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner() {
        return args -> {
            var adminAccount = accountRepository.findByUsername(ADMIN_USER_NAME);

            if (adminAccount.isEmpty()) {
                Account admin = Account.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .role(Role.ADMIN)
                        .build();

                accountRepository.save(admin);
                log.warn("Admin user has been created with default password: admin. Please change it.");
            }
            log.info("Application initialization completed.");
        };
    }
}
