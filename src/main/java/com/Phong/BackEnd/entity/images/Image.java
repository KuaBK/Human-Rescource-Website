package com.Phong.BackEnd.entity.images;

import jakarta.persistence.*;

import com.Phong.BackEnd.entity.personel.Personel;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String url;

    String cloudinaryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personel_code", referencedColumnName = "PersonelCode")
    Personel uploadedBy;
}
