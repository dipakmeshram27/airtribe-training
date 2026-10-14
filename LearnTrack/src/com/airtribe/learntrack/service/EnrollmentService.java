package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;

import java.util.List;
import java.util.Map;

public interface EnrollmentService {
    public Enrollment enrollStudent(Enrollment enrollment);
    public Map<String, List<Enrollment>> enrollmentsByStudentId(String id);
    public String completeEnrollment(String id);
    public String cancelEnrollment(String id);

}
