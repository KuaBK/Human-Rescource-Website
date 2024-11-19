package com.Phong.identityservice.dto.request.Account;

import jakarta.validation.constraints.Size;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;

    //    @DobValid(min = 18, message = "INVALID_DOB")
    //    LocalDate dob;
}
