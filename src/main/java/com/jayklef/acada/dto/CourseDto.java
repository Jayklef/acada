package com.jayklef.acada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String name;
    private Integer unit;
    private String code;
    private Long studentId;
    private Long departmentId;
}
