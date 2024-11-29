package com.Phong.BackEnd.dto.response.Employee;

import com.Phong.BackEnd.entity.personnel.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmplResponse {
    private Long personelCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private Gender gender;
    private String email;
}
