package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository {
    public Enrollment save(Enrollment enrollment);
    public List<Enrollment> findAll();
    public Optional<Enrollment> findById(String id);
    public Enrollment updateEnrollmentStatus(Enrollment enrollment);
}
