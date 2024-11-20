package com.Phong.identityservice.entity;

import java.util.Date;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "uploaded_files")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String fileName;

    String fileType;

    String fileUrl;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date uploadDate;
}
