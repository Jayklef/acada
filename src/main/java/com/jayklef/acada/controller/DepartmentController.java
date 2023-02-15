package com.jayklef.acada.controller;

import com.jayklef.acada.dto.DepartmentDto;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/new")
    public ResponseEntity<Department> saveDepartment(@RequestBody DepartmentDto departmentDto){
        Department department = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> departments(){
        List<Department> departments = departmentService.findAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.FOUND);
    }
}
