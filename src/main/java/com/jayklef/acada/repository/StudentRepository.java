package com.jayklef.acada.repository;

import com.jayklef.acada.entity.Department;
import com.jayklef.acada.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByFirstname(String firstname);

    List<Student> findStudentsByDepartment (Department department);
}
