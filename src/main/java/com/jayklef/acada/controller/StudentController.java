package com.jayklef.acada.controller;

import com.jayklef.acada.dto.StudentDto;
import com.jayklef.acada.entity.Student;
import com.jayklef.acada.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public ResponseEntity<Student> findStudent(@PathVariable("studentId") Long id) throws Exception {
        Student student = studentService.findStudent(id);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<Student> findByFirstname(@RequestParam String firstname){
        Student student = studentService.findByFirstname(firstname);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Long id, StudentDto studentDto){
        Student studentToUpdate = studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>(studentToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("studentId") Long id) throws Exception {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
