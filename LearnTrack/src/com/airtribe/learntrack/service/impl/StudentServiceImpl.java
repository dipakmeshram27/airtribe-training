package com.airtribe.learntrack.service.impl;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.StudentValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public Student addStudent(Student student) {
        StudentValidator.validate(student);
        student.setActive(true);
        student.setId(IdGenerator.getNextStudentId("STU"));
        return studentRepository.save(student);
    }

    @Override
    public String removeStudent(String id) {
        Student stu = studentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));
        stu.setActive(false);
        studentRepository.updateStudent(stu);
        return "Student deactivated successfully";
    }

    @Override
    public List<Student> listStudents() {
        return studentRepository.findAll()
                .stream()
                .filter(Student::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public Student studentById(String id) {
        return studentRepository.findById(id)
                .filter(Student::isActive)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));
    }
}
