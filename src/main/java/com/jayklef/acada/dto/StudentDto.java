package com.jayklef.acada.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String firstname;
    private String lastname;
    private String email;
    private String birthDate;
    private String address;
    private String state;
    private Long departmentId;

}
