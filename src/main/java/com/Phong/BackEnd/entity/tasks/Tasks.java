package com.Phong.BackEnd.entity.tasks;
import com.Phong.BackEnd.entity.files.File;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personnel.Employee;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Long tasksId;

    @Column(name = "title")
    String title;

    @Column(name = "Description")
    String description;

    @Column(name = "due")
    LocalDateTime due;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    StatusTasks status;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Projects project;

    @ManyToOne
    @JoinColumn(name = "employee_code")
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitted_file_id")
    File submittedFile;
}
