package com.jayklef.acada.service;

import com.jayklef.acada.dto.CourseDto;
import com.jayklef.acada.entity.Course;

import java.util.List;

public interface CourseService {

    Course saveCourse(CourseDto courseDto);

    List<Course> findAllCourse();

    Course findCourse(Long id) throws Exception;

    Course updateCourse(Long id, CourseDto courseDto);
}
