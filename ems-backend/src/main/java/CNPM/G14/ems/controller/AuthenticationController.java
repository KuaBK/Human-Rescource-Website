package CNPM.G14.ems.controller;

import CNPM.G14.ems.dto.response.*;
import CNPM.G14.ems.dto.request.*;
import CNPM.G14.ems.service.AuthenticationService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        try{
            AuthenticationResponse result = authenticationService.authenticate(request);
            return ApiResponse.<AuthenticationResponse>builder()
                    .EC(0)
                    .EM("Login succeed")
                    .data(result)
                    .build();
        } catch (Exception e){
            return ApiResponse.<AuthenticationResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) {
        try{
            var result = authenticationService.introspect(request);
            return ApiResponse.<IntrospectResponse>builder()
                    .EC(0)
                    .EM("Token validate succeeded")
                    .data(result)
                    .build();
        } catch (Exception e){
            return ApiResponse.<IntrospectResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogOutRequest request) {
        try{
            authenticationService.logOut(request);
            return ApiResponse.<Void>builder()
                    .EC(0)
                    .EM("Logout succeeded")
                    .data(null)
                    .build();
        } catch (Exception e){
            return ApiResponse.<Void>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }


    }

    @PostMapping("/refresh-token")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request){
        try {
            AuthenticationResponse result = authenticationService.refreshToken(request);
            return ApiResponse.<AuthenticationResponse>builder()
                    .EC(0)
                    .EM("Token refresh succeeded")
                    .data(result)
                    .build();
        } catch (Exception e) {
            return ApiResponse.<AuthenticationResponse>builder()
                    .EC(-1)
                    .EM(e.getMessage())
                    .data(null)
                    .build();
        }
    }

}
