package CNPM.G14.ems.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        // register CORS configurations based on URL patterns.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // holds the actual CORS settings (allowed origins, headers, and methods)
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));

        // apply to all endpoints (/**), allowing the specified CORS settings for all routes in the application.
        source.registerCorsConfiguration("/**", config);

        // wraps the CorsFilter, configuring it to be used as a servlet filter
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));

        // Ensures that the CORS filter is applied before other filters in the application.
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }
}
