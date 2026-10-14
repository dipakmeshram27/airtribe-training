package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    public Student addStudent(Student student);
    public String removeStudent(String id);
    public List<Student> listStudents();
    public Student studentById(String id);
}
