package com.jayklef.acada.service;

import com.jayklef.acada.dto.CourseDto;
import com.jayklef.acada.entity.Course;
import com.jayklef.acada.entity.Student;

import java.math.BigDecimal;
import java.util.List;

public interface CourseService {

    Course saveCourse(CourseDto courseDto);

    List<Course> findAllCourse();

    Course findCourse(Long id) throws Exception;

    Course updateCourse(Long id, CourseDto courseDto);

    void deleteCourse(Long id) throws Exception;

    Course findByName(String name);

    Long findTotalCoursesByStudent(Student student);
}
