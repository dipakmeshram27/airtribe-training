package com.airtribe.learntrack.util;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.InvalidInputException;

public class StudentValidator {
    public static void validate(Student student){
        if (student == null){
            throw new InvalidInputException("Student cannot be null");
        }
        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()){
            throw new InvalidInputException("First name is required");
        }
        if (student.getLastName() == null || student.getLastName().trim().isEmpty()){
            throw new InvalidInputException("Last name is required");
        }
        if (student.getEmail() == null || student.getEmail().trim().isEmpty()){
            throw new InvalidInputException("Email is required");
        }
        if (student.getBatch() == null || student.getBatch().trim().isEmpty()){
            throw new InvalidInputException("Batch is required");
        }
    }
}
