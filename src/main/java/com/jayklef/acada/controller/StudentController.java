package com.jayklef.acada.controller;

import com.jayklef.acada.dto.StudentDto;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.entity.Student;
import com.jayklef.acada.exception.ResourceNotFoundException;
import com.jayklef.acada.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/new")
    public ResponseEntity<Student> saveStudent(@RequestBody StudentDto studentDto){
        Student newStudent = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.findAllStudents();
        return new ResponseEntity<>(students, HttpStatus.FOUND);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> findStudent(@PathVariable("id") Long id) throws Exception {
        Student student = studentService.findStudent(id);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<Student> findByFirstname(@RequestParam String firstname){
        Student student = studentService.findByFirstname(firstname);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
                                                 @RequestBody StudentDto studentDto){
        Student studentToUpdate = studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>(studentToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) throws Exception {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/students/total")
    public Long totalStudents(@RequestParam List<Student> students){
       return studentService.calculateTotalStudents(students);
    }

    @GetMapping("/students/fee")
    public  BigDecimal studentFee(@PathVariable Department department, @PathVariable Student student) throws Exception {
        return studentService.findStudentFee(department, student);
    }

    @GetMapping("/student/fees")
    public ResponseEntity<Map<String, BigDecimal>> studentAndFees(@RequestParam String firstname,
                                                                   @RequestParam BigDecimal schoolFee){
        Map<String, BigDecimal> studentFees = studentService.findStudentAndFees(firstname, schoolFee);
        return new ResponseEntity<>(studentFees, HttpStatus.FOUND);
    }

    @GetMapping("/students/totalfees")
    public BigDecimal totalSchoolFees(@RequestParam Department department){
        return studentService.calculateTotalFees(department);
    }

    @GetMapping("all/students")
    public ResponseEntity<List<Student>> getAllStudentByDepartment(@RequestParam Department department){
        List<Student> students = studentService.findAllStudentsByDepartment(department);
        return new ResponseEntity<>(students, HttpStatus.FOUND);
    }

   /* @GetMapping("all/students")
    public Page<Student> studentsPagination(@PathVariable("pageNumber") Integer pageNumber,
                                            @PathVariable("pageSize") Integer pageSize){
        return studentService.findAllStudents(pageNumber, pageSize);
    }  */

}
