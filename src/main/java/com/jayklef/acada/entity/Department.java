package com.jayklef.acada.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String deptCode;
    private String location;
    private BigDecimal schoolFee;

    /*@OneToMany(mappedBy = "student")
    private Set<Student> students;

    @OneToMany(mappedBy = "course")
    private Set<Course> courses; */
}
