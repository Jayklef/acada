package com.jayklef.acada.service;

import com.jayklef.acada.dto.DepartmentDto;
import com.jayklef.acada.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Department saveDepartment(DepartmentDto departmentDto);

    List<Department> findAllDepartments();

    Department findDepartment(Long id) throws Exception;

    Department updateDepartment(Long id, DepartmentDto departmentDto);

    void deleteDepartment(Long id) throws Exception;

}
