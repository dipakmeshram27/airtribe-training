package com.airtribe.learntrack.repository.impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepositoryImpl implements CourseRepository {

    private final List<Course> courseList = new ArrayList();

    @Override
    public Course save(Course course) {
        courseList.add(course);
        return course;
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseList);
    }

    @Override
    public Optional<Course> findById(String id) {
        return courseList.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public Course updateCourseStatus(Course course) {
        for (Course existingCourse : courseList) {
            if (existingCourse.getId().equals(course.getId())) {
                existingCourse.setActive(course.isActive());
                return existingCourse;
            }
        }
        throw new EntityNotFoundException("Course not found");
    }
}
