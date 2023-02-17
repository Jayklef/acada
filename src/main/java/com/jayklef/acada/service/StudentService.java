package com.jayklef.acada.service;

import com.jayklef.acada.dto.StudentDto;
import com.jayklef.acada.entity.Student;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface StudentService {

    Student saveStudent(StudentDto studentDto);

    List<Student> findAllStudents();

    Student findStudent(Long id) throws Exception;

    Student updateStudent(Long id, StudentDto studentDto);

    void deleteById(Long id) throws Exception;

    Student findByFirstname(String firstname);

    Long calculateTotalStudents(List<Student> students);
}
