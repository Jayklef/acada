package com.jayklef.acada.service;

import com.jayklef.acada.dto.StudentDto;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface StudentService {

    Student saveStudent(StudentDto studentDto);

    List<Student> findAllStudents();

    Student findStudent(Long id) throws Exception;

    Student updateStudent(Long id, StudentDto studentDto);

    void deleteById(Long id) throws Exception;

    Student findByFirstname(String firstname);

    Long calculateTotalStudents(List<Student> students);

    BigDecimal findStudentFee(Department department, Student student) throws Exception;

    Map<String, BigDecimal> findStudentAndFees(String firstname, BigDecimal schoolFee);

    BigDecimal calculateTotalFees(Department department);

    List<Student> findAllStudentsByDepartment(Department department);

    Page<Student> findAllStudents(Integer pageNumber, Integer pageSize);
}
