package com.Phong.BackEnd.dto.response.Employee;

import com.Phong.BackEnd.entity.personel.Sex;
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
    private String address;
    private Sex sex;
    private String email;
}
