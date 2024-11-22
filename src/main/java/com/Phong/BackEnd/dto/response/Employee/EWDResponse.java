package com.Phong.BackEnd.dto.response.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EWDResponse {
    private String departmentName;
    private List<EmplResponse> employees;
}
