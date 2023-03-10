package com.jayklef.acada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    private String fileName;
    private String fileType;
    private String downloadUrl;
    private Long fileSize;
}
