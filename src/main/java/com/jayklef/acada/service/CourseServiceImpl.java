package com.jayklef.acada.service;

import com.jayklef.acada.dto.CourseDto;
import com.jayklef.acada.entity.Course;
import com.jayklef.acada.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(CourseDto courseDto){
        Course newCourse = new Course();
        newCourse.setName(courseDto.getName());
        newCourse.setUnit(courseDto.getUnit());
        newCourse.setCode(courseDto.getCode());

        return courseRepository.save(newCourse);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAll();
    }
}
