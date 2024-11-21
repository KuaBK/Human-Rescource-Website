package com.Phong.BackEnd.entity.tasks;

import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personel.Employee;
import com.Phong.BackEnd.entity.projects.Projects;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TasksId")
    String tasksId;

    @Column(name = "Description")
    String description;

    @Column(name = "DeadLine")
    LocalDateTime deadline;

    @Enumerated(EnumType.STRING)
    @Column(name = "Stastus")
    StatusTasks status;

    @ManyToOne
    @JoinColumn(name = "ProjectId")
    Projects project;

    @OneToOne
    @JoinColumn(name = "EmployeeId")
    Employee employee;
}
