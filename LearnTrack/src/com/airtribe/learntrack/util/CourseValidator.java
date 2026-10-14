package com.airtribe.learntrack.util;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.InvalidInputException;

public class CourseValidator {
    public static void validate(Course course){
        if (course == null){
            throw new InvalidInputException("Course cannot be null");
        }
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()){
            throw new InvalidInputException("Course name is required");
        }
        if (course.getDescription() == null || course.getDescription() .trim().isEmpty()){
            throw new InvalidInputException("Course description is required");
        }
        if (course.getDurationInWeek() == null || course.getDurationInWeek().trim().isEmpty()){
            throw new InvalidInputException("Duration is required");
        }
    }
}
