package com.airtribe.learntrack.service.impl;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.util.CourseValidator;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course addCourse(Course course) {
        CourseValidator.validate(course);
        course.setActive(false);
        course.setId(IdGenerator.getNextCourseId("CR"));
        return courseRepository.save(course);
    }

    @Override
    public String activateCourse(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Course id not found"));
        course.setActive(true);
        courseRepository.save(course);
        return "Course activated successfully";
    }

    @Override
    public String deactivateCourse(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Course id not found"));
        course.setActive(false);
        courseRepository.save(course);
        return "Course deactivated successfully";
    }

    @Override
    public List<Course> listCourses() {
        return new ArrayList<>(courseRepository.findAll());
    }

    @Override
    public List<Course> listActiveCourses() {
        return courseRepository.findAll()
                .stream()
                .filter(Course::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> listDeactivatedCourses() {
        return courseRepository.findAll()
                .stream()
                .filter(course ->!course.isActive())
                .collect(Collectors.toList());
    }
}
