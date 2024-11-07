package com.Phong.identityservice.dto.request;

import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 9, max = 24, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    //    @DobValid(min = 18, message = "INVALID_DOB")
    //    LocalDate dob;
}
