package com.Phong.BackEnd.entity.files;

import java.util.Date;

import com.Phong.BackEnd.entity.personnel.Personnel;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "file_name")
    String fileName;

    @Column(name = "file_type")
    String fileType;

    @Column(name = "file_url")
    String fileUrl;

    @Column(name = "upload_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_code", referencedColumnName = "Code")
    @JsonBackReference
    Personnel uploadedBy;
}
