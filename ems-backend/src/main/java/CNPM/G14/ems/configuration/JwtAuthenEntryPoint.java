package CNPM.G14.ems.configuration;

import CNPM.G14.ems.dto.response.ApiResponse;
import CNPM.G14.ems.exception.ErrorCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

// Interface for handling cases when an unauthenticated user tries to access a protected resource.
public class JwtAuthenEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;

        response.setStatus(errorCode.getStatusCode().value());

        // Sets the Content-Type of the response to JSON (application/json)
        // As the response body will contain a JSON payload.
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .EC(errorCode.getCode())
                .EM(errorCode.getMessage())
                .data(null)
                .build();

        // An "ObjectMapper" converts the "ApiResponse" object into a JSON string.
        ObjectMapper objectMapper = new ObjectMapper();

        // Writes this JSON string to the response’s output stream
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));

        // Forces any remaining data to be sent to the client immediately.
        response.flushBuffer();
    }
}
