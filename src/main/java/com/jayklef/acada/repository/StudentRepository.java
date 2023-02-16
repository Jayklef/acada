package com.jayklef.acada.repository;

import com.jayklef.acada.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByFirstname(String firstname);
}
