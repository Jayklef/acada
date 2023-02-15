package com.jayklef.acada.service;

import com.jayklef.acada.dto.DepartmentDto;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Department saveDepartment(DepartmentDto departmentDto) {
        Department newDepartment = new Department();
        newDepartment.setName(departmentDto.getName());
        newDepartment.setDeptCode(departmentDto.getDeptCode());
        newDepartment.setLocation(departmentDto.getLocation());

        return departmentRepository.save(newDepartment);
    }

    @Override
    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }
}
