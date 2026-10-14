package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    public Course save(Course course);
    public List<Course> findAll();
    public Optional<Course> findById(String id);
    public Course updateCourseStatus(Course course);
}
