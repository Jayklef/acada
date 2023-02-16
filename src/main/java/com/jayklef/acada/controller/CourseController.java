package com.jayklef.acada.controller;

import com.jayklef.acada.dto.CourseDto;
import com.jayklef.acada.entity.Course;
import com.jayklef.acada.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/new")
    public ResponseEntity<Course> saveCourse(@RequestBody CourseDto courseDto){
        Course newCourse = courseService.saveCourse(courseDto);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Course>> findAllCourses(){
        List<Course> courses = courseService.findAllCourse();
        return new ResponseEntity<>(courses, HttpStatus.FOUND);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Course> findCourse(@PathVariable("courseId") Long id) throws Exception {
        Course course = courseService.findCourse(id);
        return new ResponseEntity<>(course, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Long id,
                                               @RequestBody CourseDto courseDto){
        Course courseToUpdate = courseService.updateCourse(id, courseDto);
        return new ResponseEntity<>(courseToUpdate, HttpStatus.OK);
    }

}
