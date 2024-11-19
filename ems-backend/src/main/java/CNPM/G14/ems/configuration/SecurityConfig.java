package CNPM.G14.ems.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity

// Allowing to secure individual methods with annotations like @PreAuthorize or @Secured.
@EnableMethodSecurity
public class SecurityConfig {
    private static final String[] PUBLIC_ENDPOINTS = {
            "/users", "/auth/token", "/auth/introspect", "/auth/login", "/auth/refresh", "/auth/logout",
            "/account", "/account/create", "/account/info", "/account/all", "/account/delete", "/account/edit",
            "/personel", "/personel/add", "/personel/all" , "/personel/delete", "/personel/edit",
            "/employee", "/employee/add", "/employee/all", "/employee/dept", "employee/select-dept", "employee/edit", "employee/delete",
            "/manager", "/manager/add", "/manager/assign", "/manager/remove", "/manager/all",
            "/project", "/project/add", "/project/all", "/project/assign", "/project/remove", "project/dept",
            "/department", "/department/create", "/department/all", "/department/delete",
            "/task", "/task/create", "/task/all", "/task/update-status", "/task/project", "/task/assign", "/task/employee",
            "/absence", "/absence/create", "/absence/employee",
            "/salary", "/salary/create", "/salary/edit", "/salary/payrate-edit", "/salary/bonus-penalty", "/salary/employee",
            "/salary/employee/month-year",
            "/attendance/specific", "/attendance/check-in", "/attendance/check-out", "/attendance/employee", "/attendance/today-duration",
    };

    @Autowired
    private final CustomJwtDecoder customJwtDecoder;
    public SecurityConfig(CustomJwtDecoder customJwtDecoder) {
        this.customJwtDecoder = customJwtDecoder;
    }

    @Bean
    // Defines the main security filter chain for the application
    // Determining access rules and authentication mechanisms.
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
                .anyRequest()
                .authenticated()
        );

        // Set up the application as an OAuth2 resource server, using JWT tokens for authentication.
        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder)
        // Converts JWT claims to Spring Security GrantedAuthority roles using a custom converter.
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenEntryPoint()));

        // Often disabled in stateless REST APIs that don’t maintain user sessions.
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    // Customize how JWT claims are converted to GrantedAuthority roles.
    JwtAuthenticationConverter jwtAuthenticationConverter() {

        // Converts JWT claims to granted authorities
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Removes any prefix from roles (e.g., “ROLE_”), which can simplify role checking.
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        // Configures Spring Security to use these authorities for role-based access control.
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
