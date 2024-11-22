package com.Phong.BackEnd.dto.response.Employee;

import com.Phong.BackEnd.entity.personel.Position;
import com.Phong.BackEnd.entity.personel.Sex;

import com.Phong.BackEnd.entity.tasks.Tasks;
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
    String address;
    String avatar;
    Sex sex;
    Position position;
    String departmentName;
    List<String> taskList;
    Integer tasksCompleteNumber;
    List<String> projectList;
    Integer projectInvolved;
}
