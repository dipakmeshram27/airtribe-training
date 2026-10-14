package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    public Student save(Student student);
    public Student updateStudent(Student student);
    public List<Student> findAll();
    public Optional<Student> findById(String id);
}
