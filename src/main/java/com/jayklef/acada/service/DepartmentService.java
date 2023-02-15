package com.jayklef.acada.service;

import com.jayklef.acada.dto.DepartmentDto;
import com.jayklef.acada.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(DepartmentDto departmentDto);

    List<Department> findAllDepartments();
}
