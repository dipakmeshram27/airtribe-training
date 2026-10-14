package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.util.DateConverter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Enrollment {
    private String id;
    private String studentId;
    private String courseId;
    private Instant enrollmentDate;
    private String status;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Instant getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Instant enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void displayEnrollment(){
        System.out.println("\n========Enrollment Details=======");
        System.out.println("Enrollment ID: "+id);
        System.out.println("Student ID: "+studentId);
        System.out.println("Course ID: "+courseId);
        System.out.println("Enrollment Date: "+ DateConverter.convertToDate(enrollmentDate));
        System.out.println("Status: "+status);
    }
}
