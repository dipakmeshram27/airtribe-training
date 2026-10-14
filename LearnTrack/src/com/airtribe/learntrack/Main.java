package com.airtribe.learntrack;


import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.repository.impl.CourseRepositoryImpl;
import com.airtribe.learntrack.repository.impl.EnrollmentRepositoryImpl;
import com.airtribe.learntrack.repository.impl.StudentRepositoryImpl;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.service.impl.CourseServiceImpl;
import com.airtribe.learntrack.service.impl.EnrollmentServiceImpl;
import com.airtribe.learntrack.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main(StudentService studentService,
                CourseService courseService,
                EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }

    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepositoryImpl();
        CourseRepository courseRepository = new CourseRepositoryImpl();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepositoryImpl();

        StudentService studentService =
                new StudentServiceImpl(studentRepository);

        CourseService courseService =
                new CourseServiceImpl(courseRepository);

        EnrollmentService enrollmentService =
                new EnrollmentServiceImpl(
                        courseRepository,
                        enrollmentRepository,
                        studentRepository
                );


        Main app = new Main(
                studentService,
                courseService,
                enrollmentService
        );

        app.start();
    }

    public void start() {
        boolean running = true;

        while (running) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Student Management");
            System.out.println("2. Course Management");
            System.out.println("3. Enrollment Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> studentMenu();
                    case 2 -> courseMenu();
                    case 3 -> enrollmentMenu();
                    case 0 -> {
                        running = false;
                        System.out.println("Application closed.");
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }

        scanner.close();
    }

    // ================= STUDENT MENU =================

    private void studentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== STUDENT MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Deactivate Student");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewAllStudents();
                    case 3 -> searchStudent();
                    case 4 -> deactivateStudent();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter student last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter batch name: ");
        String batchName = scanner.nextLine().trim();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batchName);

        Student saved = studentService.addStudent(student);

        System.out.println("Student added successfully. ID: "
                + saved.getId());
    }

    private void viewAllStudents() {
        List<Student> students = studentService.listStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        students.forEach(Student::getStudentDetails);
    }

    private void searchStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        Optional<Student> student = Optional.ofNullable(studentService.studentById(id));

        student.ifPresent(Student::getStudentDetails);
    }

    private void deactivateStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine().trim();

        studentService.removeStudent(id);

        System.out.println("Student deactivated successfully.");
    }

    // ================= COURSE MENU =================

    private void courseMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== COURSE MANAGEMENT =====");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Activate Course");
            System.out.println("4. Deactivate Course");
            System.out.println("5. All Activated Courses");
            System.out.println("6. All Deactivated Courses");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> addCourse();
                    case 2 -> viewAllCourses();
                    case 3 -> activateCourse();
                    case 4 -> deactivateCourse();
                    case 5 -> activatedCourses();
                    case 6 -> deactivatedCourses();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void addCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine().trim();

        System.out.print("Enter course description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter course duration: ");
        String duration = scanner.nextLine().trim();

        Course course = new Course();
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeek(duration);

        Course saved = courseService.addCourse(course);

        System.out.println("Course added successfully. ID: "
                + saved.getId());
    }

    private void viewAllCourses() {
        List<Course> courses = courseService.listCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        courses.forEach(Course::displayCourse);
    }

    private void activateCourse() {
        System.out.print("Enter course ID: ");
        String id = scanner.nextLine().trim();

        String result = courseService.activateCourse(id);

        System.out.println(result);
    }

    private void deactivateCourse() {
        System.out.print("Enter course ID: ");
        String id = scanner.nextLine().trim();

        String result = courseService.deactivateCourse(id);

        System.out.println(result);
    }

    private void activatedCourses(){
        List<Course> courses = courseService.listActiveCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        courses.forEach(Course::displayCourse);
    }

    private void deactivatedCourses(){
        List<Course> courses = courseService.listDeactivatedCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        courses.forEach(Course::displayCourse);
    }

    // ================= ENROLLMENT MENU =================

    private void enrollmentMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\n===== ENROLLMENT MANAGEMENT =====");
            System.out.println("1. Enroll Student");
            System.out.println("2. View Student Enrollments");
            System.out.println("3. Complete Enrollment");
            System.out.println("4. Cancel Enrollment");
            System.out.println("0. Back");
            System.out.print("Enter your choice: ");

            int choice = readInt();

            try {
                switch (choice) {
                    case 1 -> enrollStudent();
                    case 2 -> viewStudentEnrollments();
                    case 3 -> completeEnrollment();
                    case 4 -> cancelEnrollment();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void enrollStudent() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine().trim();

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);

        Enrollment saved = enrollmentService.enrollStudent(enrollment);

        System.out.println("Enrollment successful. ID: "
                + saved.getId());
    }

    private void viewStudentEnrollments() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine().trim();

        Map<String, List<Enrollment>> enrollments =
                enrollmentService.enrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            System.out.println("No enrollments found.");
            return;
        } else {
            enrollments.forEach((studentIdKey, enrollmentList) -> {
                System.out.println("Student ID: " + studentIdKey);

                enrollmentList.forEach(Enrollment::displayEnrollment);
            });
        }

    }

    private void completeEnrollment() {
        System.out.print("Enter enrollment ID: ");
        String id = scanner.nextLine().trim();

        String result = enrollmentService.completeEnrollment(id);

        System.out.println(result);
    }

    private void cancelEnrollment() {
        System.out.print("Enter enrollment ID: ");
        String id = scanner.nextLine().trim();

        String result = enrollmentService.cancelEnrollment(id);

        System.out.println(result);
    }

    // ================= INPUT HELPERS =================

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid number: ");
            }
        }
    }

    private Long readLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.print("Enter a valid ID: ");
            }
        }
    }
}
