package com.airtribe.learntrack.repository.impl;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final List<Enrollment> enrollmentList = new ArrayList<>();

    @Override
    public Enrollment save(Enrollment enrollment) {
        enrollmentList.add(enrollment);
        return enrollment;
    }

    @Override
    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollmentList);
    }

    @Override
    public Optional<Enrollment> findById(String id) {
        return enrollmentList.stream()
                .filter(enrollmentId->enrollmentId.getId().equals(id))
                .findFirst();
    }

    @Override
    public Enrollment updateEnrollmentStatus(Enrollment enrollment) {
        for (Enrollment existingEnrollment : enrollmentList) {
            if (existingEnrollment.getId().equals(enrollment.getId())) {
                existingEnrollment.setStatus(enrollment.getStatus());
                return existingEnrollment;
            }
        }
        throw new EntityNotFoundException("Enrollment not found");
    }
}
