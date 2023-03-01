package com.jayklef.acada.service;

import com.jayklef.acada.dto.StudentDto;
import com.jayklef.acada.entity.Course;
import com.jayklef.acada.entity.Department;
import com.jayklef.acada.entity.Student;
import com.jayklef.acada.exception.ResourceNotFoundException;
import com.jayklef.acada.repository.DepartmentRepository;
import com.jayklef.acada.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        Optional<Department> department = departmentRepository.findById(studentDto.getDepartmentId().getId());
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
            throw new ResourceNotFoundException("student whose Id is  " + id + "not found");
        }
        return student.get();
    }

    @Override
    public Student updateStudent(Long id, StudentDto studentDto) {
        Student studentInDb = studentRepository.findById(id).get();

        if (Objects.nonNull(studentDto.getFirstname()) &&
        !"".equalsIgnoreCase(studentDto.getFirstname())){
            studentInDb.setFirstname(studentDto.getFirstname());
        }

        if (Objects.nonNull(studentDto.getLastname()) &&
        !"".equalsIgnoreCase(studentDto.getLastname())){
            studentInDb.setLastname(studentDto.getLastname());
        }

        if (Objects.nonNull(studentDto.getBirthDate())&&
        !"".equalsIgnoreCase(studentDto.getBirthDate())){
            studentInDb.setBirthDate(studentDto.getBirthDate());
        }

        if (Objects.nonNull(studentDto.getEmail()) &&
        !"".equalsIgnoreCase(studentDto.getEmail())){
            studentInDb.setEmail(studentDto.getEmail());
        }

        if (Objects.nonNull(studentDto.getAddress()) &&
        !"".equalsIgnoreCase(studentDto.getAddress())){
            studentInDb.setAddress(studentDto.getAddress());
        }

        if (Objects.nonNull(studentDto.getState()) &&
        !"".equalsIgnoreCase(studentDto.getState())){
            studentInDb.setState(studentDto.getState());
        }

        if (Objects.nonNull(studentDto.getDepartmentId()) &&
        !"".equalsIgnoreCase(studentDto.getDepartmentId().toString())){
            Optional<Department> department = departmentRepository.findById(studentDto.getDepartmentId().getId());
            studentInDb.setDepartment(department.get());
        }

        return studentRepository.save(studentInDb);
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Student student = findStudent(id);
        studentRepository.deleteById(student.getId());
    }

    @Override
    public Student findByFirstname(String firstname) {
        Student student = studentRepository.findByFirstname(firstname);
        return student;
    }

    @Override
    public Long calculateTotalStudents(List<Student> students){

        List<Student> totalStudent = findAllStudents();
        return (long) totalStudent.size();

       /* Long totalStudents = studentRepository.count();
        return totalStudents; */
    }

    @Override
    public BigDecimal findStudentFee(Department department, Student student) throws Exception {

        Student studentInDb = findStudent(student.getId());
        if (studentInDb == null){
            throw new ResourceNotFoundException("Student with id of " + student.getId() + "not found");
        }
        return studentInDb.getDepartment().getSchoolFee();
    }

    @Override
    public Map<String, BigDecimal> findStudentAndFees(String firstname, BigDecimal schoolFee) {
        Map<String, BigDecimal> fees = new HashMap<>();

      //  Map<String, BigDecimal> fees = (Map<String, BigDecimal>) studentRepository.findAll();

        for (Map.Entry<String, BigDecimal> entry : fees.entrySet()){
            String f = entry.getKey();
            BigDecimal s = entry.getValue();
        }

          return fees;
    }

    @Override
    public BigDecimal calculateTotalFees(Department department) {

        Student student = new Student();
        BigDecimal sum = new BigDecimal(0);

        BigDecimal fee = student.getDepartment().getSchoolFee();

        BigDecimal totalFee = fee.add(sum);
        return totalFee;


      /*  List<Student> studentsFees = findAllStudents()
                .stream().map(s -> s.getDepartment().getSchoolFee().add(sum))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return studentsFees;   */

    }

    @Override
    public List<Student> findAllStudentsByDepartment(Department department){
        return studentRepository.findStudentsByDepartment(department);
    }

    @Override
    public Page<Student> findAllStudents(Integer pageNumber, Integer pageSize) {
        return studentRepository.findAllStudent(pageNumber, pageSize);
    }

}
