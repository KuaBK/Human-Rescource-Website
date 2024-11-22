package com.Phong.BackEnd.entity.files;

import java.util.Date;

import com.Phong.BackEnd.entity.personel.Personel;
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

    String fileName;

    String fileType;

    String fileUrl;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date uploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_code", referencedColumnName = "PersonelCode")
    @JsonBackReference
    Personel uploadedBy;
}
