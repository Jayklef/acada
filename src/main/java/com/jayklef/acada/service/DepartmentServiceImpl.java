package com.jayklef.acada.service;

import com.jayklef.acada.dto.DepartmentDto;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.exception.ResourceNotFoundException;
import com.jayklef.acada.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        newDepartment.setSchoolFee(departmentDto.getSchoolFee());

        return departmentRepository.save(newDepartment);
    }

    @Override
    public List<Department> findAllDepartments(){
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartment(Long id) throws Exception {

        Optional<Department> department = departmentRepository.findById(id);

        if (!department.isPresent()){
            throw new ResourceNotFoundException("Department with id:" + id + "does not exist");
        }
        return department.get();
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDto departmentDto) {

        Department departmentInDb = departmentRepository.findById(id).get();

        if (Objects.nonNull(departmentDto.getName()) &&
        !"".equalsIgnoreCase(departmentDto.getName())){
            departmentInDb.setName(departmentDto.getName());
        }

        if (Objects.nonNull(departmentDto.getDeptCode()) &&
        !"".equalsIgnoreCase(departmentDto.getDeptCode())){
            departmentInDb.setDeptCode(departmentDto.getDeptCode());
        }

        if (Objects.nonNull(departmentDto.getLocation()) &&
        !"".equalsIgnoreCase(departmentDto.getLocation())){
            departmentInDb.setLocation(departmentDto.getLocation());
        }

        return departmentRepository.save(departmentInDb);
    }

    @Override
    public void deleteDepartment(Long id) throws Exception {
        Department department = findDepartment(id);

        if (department == null){
            throw new ResourceNotFoundException("Department with Id of " + id + "not found");
        }

        departmentRepository.deleteById(id);
    }
}
