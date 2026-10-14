package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;

import java.util.List;

public interface CourseService {
    public Course addCourse(Course course);
    public String activateCourse(String id);
    public String deactivateCourse(String id);
    public List<Course> listCourses();
    public List<Course> listActiveCourses();
    public List<Course> listDeactivatedCourses();
}
