package com.Phong.BackEnd.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {
        "/auth/login", "/auth/introspect", "/auth/logout", "/auth/refresh",
        "/account/myInfo", "/account", "/account/create", "/account/getAll",

        "/employee", "/employee/create", "/employee/update", "/employee/assign-to-department", "/employee/delete",
        "/employee/department", "/employee/account", "/employee/all",

        "/managers", "/managers/create", "/managers/account", "/managers/all", "/managers/update",
        "/managers/delete", "/managers/assign", "/managers/remove",

        "/attendance/checkIn/{employeeId}", "/attendance/checkOut/{employeeId}",

        "/departments", "/departments/create", "/departments/all", "/departments/update", "/departments/delete",

       "/projects", "/projects/create", "/projects/all", "/projects/update", "/projects/delete",
       "/projects/assign", "/projects/remove", "/projects/department", "/projects/employees",

        "/tasks", "/tasks/create", "/tasks/all", "/tasks/status-update", "/tasks/assign",
        "/tasks/project", "/tasks/employee", "/tasks/submit", "/tasks/unsubmit",

        "/attendance/checkIn", "/attendance/checkOut", "/attendance/workingDuration", "/attendance/all/employee",
        "/attendance/date", "/attendance/today", "/attendance/month-year",

        "/salary", "/salary/edit", "/salary/create", "/salary/payrate-edit", "/salary/bonus-penalty",
        "/salary/employee", "/salary/employee/month-year", "/salary/all",

        "/images", "/images/upload",
        "/files", "/files/upload", "/files/personnel",

    };

    private final CustomJwtDecoder customJwtDecoder;


    public SecurityConfig(CustomJwtDecoder customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(PUBLIC_ENDPOINTS)
                .permitAll()
                .anyRequest()
                .authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenEntryPoint()));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
