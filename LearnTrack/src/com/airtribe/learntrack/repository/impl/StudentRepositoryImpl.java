package com.airtribe.learntrack.repository.impl;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl implements StudentRepository {

    private final List<Student> studentList = new ArrayList();

    public StudentRepositoryImpl() {
    }

    @Override
    public Student save(Student student) {
        studentList.add(student);
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        for(Student existingStudent: studentList){
            if (existingStudent.getId().equals(student.getId())){
                existingStudent.setActive(student.isActive());
                return existingStudent;
            }
        }
        throw new EntityNotFoundException("Student not found");
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentList);
    }

    @Override
    public Optional<Student> findById(String id) {
        return studentList.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }
}
