package com.Phong.BackEnd.dto.request.Employee;

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
public class EmployeeUpdateRequest {
    String email;
    String firstName;
    String lastName;
    String phone;
    String city;
    String street;
    Gender gender;
    Position position;
    Long departmentId;
    List<String> TaskList;
    Integer tasksCompleteNumber;
    List<String> projectList;
    Integer projectsInvolved;
}
