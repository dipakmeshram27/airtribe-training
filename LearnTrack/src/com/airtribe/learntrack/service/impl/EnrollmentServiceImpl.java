package com.airtribe.learntrack.service.impl;

import com.airtribe.learntrack.constants.Constants;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.util.EnrollmentValidator;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Enrollment enrollStudent(Enrollment enrollment) {
        Student student = studentRepository.findById(enrollment.getStudentId())
                .filter(Student::isActive)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));

        Course course = courseRepository.findById(enrollment.getCourseId())
                .filter(Course::isActive)
                .orElseThrow(()-> new EntityNotFoundException("Course not found"));

        EnrollmentValidator.validate(enrollment);

        enrollment.setId(IdGenerator.getNextEnrollmentId("ENROLL"));
        enrollment.setEnrollmentDate(Instant.now());
        enrollment.setStatus(Constants.ACTIVE);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Map<String, List<Enrollment>> enrollmentsByStudentId(String id) {
        return enrollmentRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Enrollment::getStudentId));
    }

    @Override
    public String completeEnrollment(String id) {
        Enrollment enrollmentDetails = enrollmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Enrollment not found"));
        enrollmentDetails.setStatus(Constants.COMPLETED);
        enrollmentRepository.updateEnrollmentStatus(enrollmentDetails);
        return "Enrollment completed successfully.";
    }

    @Override
    public String cancelEnrollment(String id) {
        Enrollment enrollmentDetails = enrollmentRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Enrollment not found"));
        enrollmentDetails.setStatus(Constants.CANCELLED);
        enrollmentRepository.updateEnrollmentStatus(enrollmentDetails);
        return "Enrollment Cancelled.";
    }
}
