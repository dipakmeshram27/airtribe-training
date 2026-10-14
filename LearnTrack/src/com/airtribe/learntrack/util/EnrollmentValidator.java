package com.airtribe.learntrack.util;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.InvalidInputException;

public class EnrollmentValidator {
    public static void validate(Enrollment enrollment){
        if (enrollment == null){
            throw new InvalidInputException("Enrollment cannot be null");
        }
        if (enrollment.getStudentId() == null || enrollment.getStudentId().trim().isEmpty()){
            throw new InvalidInputException("Student id is required");
        }
        if (enrollment.getCourseId() == null || enrollment.getCourseId().trim().isEmpty()){
            throw new InvalidInputException("Course id is required");
        }
    }
}
