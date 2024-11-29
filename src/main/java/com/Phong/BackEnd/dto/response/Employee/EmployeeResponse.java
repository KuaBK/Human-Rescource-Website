package com.Phong.BackEnd.dto.response.Employee;

import com.Phong.BackEnd.entity.personnel.Position;
import com.Phong.BackEnd.entity.personnel.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Long personelCode;

    String firstName;
    String lastName;
    String email;
    String phone;
    String city;
    String street;
    String avatar;
    Gender gender;
    Position position;
    String departmentName;
    List<String> taskList;
    Integer tasksCompleteNumber;
    List<String> projectList;
    Integer projectInvolved;
}
