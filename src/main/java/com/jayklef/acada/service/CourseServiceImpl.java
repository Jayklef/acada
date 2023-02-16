package com.jayklef.acada.service;

import com.jayklef.acada.dto.CourseDto;
import com.jayklef.acada.entity.Course;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.entity.Student;
import com.jayklef.acada.exception.ResourceNotFoundException;
import com.jayklef.acada.repository.CourseRepository;
import com.jayklef.acada.repository.DepartmentRepository;
import com.jayklef.acada.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private StudentRepository studentRepository;

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

    @Override
    public Course findCourse(Long id) {

        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()){
            throw new ResourceNotFoundException("Course whose id is " + id + "not found");
        }

        return course.get();
    }

    @Override
    public Course updateCourse(Long id, CourseDto courseDto) {

        Course courseInDb = courseRepository.findById(id).get();

        if (Objects.nonNull(courseDto.getName()) &&
        "".equalsIgnoreCase(courseDto.getName())){
            courseInDb.setName(courseDto.getName());
        }

        if (Objects.nonNull(courseDto.getUnit()) &&
        !"".equalsIgnoreCase(courseDto.getUnit().toString())){
            courseInDb.setUnit(courseDto.getUnit());
        }

        if (Objects.nonNull(courseDto.getCode()) &&
        !"".equalsIgnoreCase(courseDto.getCode())){
            courseInDb.setCode(courseDto.getCode());
        }

        if (Objects.nonNull(courseDto.getDepartmentId()) &&
        "".equalsIgnoreCase(courseDto.getDepartmentId().toString())){
            Optional<Department> department = departmentRepository.findById(courseDto.getDepartmentId());
            courseInDb.setDepartment(department.get());
        }

        if (Objects.nonNull(courseDto.getStudentId()) &&
        !"".equalsIgnoreCase(courseDto.getStudentId().toString())){
            Optional<Student> student = studentRepository.findById(courseDto.getStudentId());
            courseInDb.setStudents(student.stream().toList());
        }
        return courseRepository.save(courseInDb);
    }

    @Override
    public void deleteCourse(Long id) throws Exception {
        Course course = findCourse(id);
        courseRepository.deleteById(course.getId());
    }

}
