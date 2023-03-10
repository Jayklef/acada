package com.jayklef.acada.repository;

import com.jayklef.acada.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    Department findDepartmentByLocation(String location);

}
