package com.jayklef.acada.service;

import com.jayklef.acada.dto.StudentDto;
import com.jayklef.acada.entity.Course;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.entity.Student;
import com.jayklef.acada.repository.DepartmentRepository;
import com.jayklef.acada.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Student saveStudent(StudentDto studentDto) {
        Student newStudent = new Student();
        newStudent.setFirstname(studentDto.getFirstname());
        newStudent.setLastname(studentDto.getLastname());
        newStudent.setEmail(generateEmail(newStudent.getFirstname(), newStudent.getLastname()));
        newStudent.setBirthDate(studentDto.getBirthDate());
        newStudent.setAddress(studentDto.getAddress());
        newStudent.setState(studentDto.getState());
        Optional<Department> department = departmentRepository.findById(studentDto.getDepartmentId());
        newStudent.setDepartment(department.get());

        return studentRepository.save(newStudent);
    }

    private String generateEmail(String firstname, String lastname){
        String schoolId = "acada.edu";
        String studentEmail = firstname.toLowerCase() + "." + lastname.toLowerCase().concat(".") + schoolId.toLowerCase();
        System.out.println(studentEmail);
        return studentEmail;
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudent(Long id) throws Exception {

        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()){
            throw new Exception("student whose Id is  " + id + "not found");
        }
        return student.get();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student studentInDb = studentRepository.findById(id).get();

        if (Objects.nonNull(student.getFirstname()) &&
        !"".equalsIgnoreCase(student.getFirstname())){
            studentInDb.setFirstname(student.getFirstname());
        }

        return studentRepository.save(studentInDb);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Student student = findStudent(id);
        studentRepository.deleteById(student.getId());
    }
}
