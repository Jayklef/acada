package com.jayklef.acada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    @NonNull
    private String name;

    @NonNull
    private String deptCode;

    @NonNull
    private String location;

    private BigDecimal schoolFee;
    private Set<StudentDto> students;
}
