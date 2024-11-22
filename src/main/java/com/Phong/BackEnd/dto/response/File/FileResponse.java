package com.Phong.BackEnd.dto.response.File;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private Long id;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private Date uploadDate;
    private String uploadedBy;
}
