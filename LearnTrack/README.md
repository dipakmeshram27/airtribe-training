# LearnTrack

A console-based **Student Management System** written in plain Java (no frameworks, no build tool).
It lets you manage students, courses and course enrollments through an interactive text menu, with
all data held in memory for the lifetime of the process.

Built as a Java assignment for Airtribe, with a focus on layered architecture, interfaces,
collections, the Streams API and custom exceptions.

---

## Features

### Student Management
- Add a student (first name, last name, email, batch) — gets an auto-generated ID like `STU-001`
- List all **active** students
- Search a student by ID
- Deactivate a student (soft delete — the record stays, `active` flips to `false`)

### Course Management
- Add a course (name, description, duration in weeks) — gets an ID like `CR-001`, created **inactive**
- List all courses
- Activate / deactivate a course
- List only active courses, or only deactivated courses

### Enrollment Management
- Enroll a student in a course — gets an ID like `ENROLL-001`, status `Active`
  (both the student **and** the course must be active, otherwise enrollment is rejected)
- View enrollments grouped by student ID
- Mark an enrollment `Completed`
- Mark an enrollment `Cancelled`

---

## Tech Stack

| | |
|---|---|
| Language | Java 21 (uses arrow `switch`, `java.time`, Streams) |
| Build | None — compiled directly with `javac`, or run from IntelliJ IDEA |
| Storage | In-memory `ArrayList` inside each repository implementation |
| Dependencies | JDK standard library only |

---

## Project Structure

```
src/com/airtribe/learntrack/
├── Main.java                      # Entry point + all console menus and input handling
├── constants/
│   └── Constants.java             # Enrollment status values: Active / Completed / Cancelled
├── entity/
│   ├── Person.java                # Base class: id, firstName, lastName, email
│   ├── Student.java               # extends Person; adds batch + active flag
│   ├── Course.java                # id, name, description, durationInWeek, active
│   └── Enrollment.java            # id, studentId, courseId, enrollmentDate, status
├── exception/
│   ├── EntityNotFoundException.java   # unchecked — thrown when an ID doesn't resolve
│   └── InvalidInputException.java     # unchecked — thrown on failed validation
├── repository/
│   ├── StudentRepository.java     # interfaces: save / findAll / findById / update
│   ├── CourseRepository.java
│   ├── EnrollmentRepository.java
│   └── impl/                      # in-memory List-backed implementations
│       ├── StudentRepositoryImpl.java
│       ├── CourseRepositoryImpl.java
│       └── EnrollmentRepositoryImpl.java
├── service/
│   ├── StudentService.java        # business-rule interfaces
│   ├── CourseService.java
│   ├── EnrollmentService.java
│   └── impl/
│       ├── StudentServiceImpl.java
│       ├── CourseServiceImpl.java
│       └── EnrollmentServiceImpl.java
└── util/
    ├── StudentValidator.java      # null / blank-field validation
    ├── CourseValidator.java
    ├── EnrollmentValidator.java
    ├── IdGenerator.java           # thread-safe prefixed sequential IDs (STU-001, CR-001, …)
    └── DateConverter.java         # Instant → "yyyy-MM-dd" in the system time zone
```

---

## Architecture

The project follows a classic three-layer separation, wired by hand in `Main.main()`
(constructor injection — no DI container):

```
Main (console UI)
   |   reads input, prints output, catches exceptions
   v
Service layer  (StudentService, CourseService, EnrollmentService)
   |   validation, ID generation, status transitions, business rules
   v
Repository layer  (StudentRepositoryImpl, CourseRepositoryImpl, EnrollmentRepositoryImpl)
   |   stores and queries entities
   v
In-memory ArrayList
```

Key points:

- **Program to interfaces.** Every service and repository has an interface, and `Main` depends only
  on those interfaces — so swapping the in-memory repositories for a JDBC or file-based
  implementation would not touch the UI or the service layer.
- **Soft deletes.** Students and courses are never removed, only flagged inactive. Listing and
  lookup filter on `isActive()`, so a deactivated student simply disappears from queries.
- **Unchecked exceptions for flow control.** Services throw `EntityNotFoundException` or
  `InvalidInputException`; every menu loop wraps its dispatch in a `try/catch` and prints
  `Error: <message>`, so a bad ID never crashes the app.
- **ID generation** uses `ConcurrentHashMap` + `AtomicInteger` counters keyed by prefix, formatted
  as `PREFIX-%03d`. The counters are static, so they reset on every run.

---

## Getting Started

### Prerequisites
- JDK 21 or newer (check with `java -version`)


### Run from IntelliJ IDEA
1. Open the `LearnTrack` folder as a project (the `.iml` module file is already included).
2. Confirm the project SDK is set to **21** under `File -> Project Structure -> Project`.
3. Open `src/com/airtribe/learntrack/Main.java` and run its `main` method.

---

## Usage

The app opens on the main menu and loops until you choose `0`:

```
===== STUDENT MANAGEMENT SYSTEM =====
1. Student Management
2. Course Management
3. Enrollment Management
0. Exit
Enter your choice:
```

A typical first session — note that a course must be **activated** before anyone can enroll in it:

```
1 -> 1   Add Student           -> Student added successfully. ID: STU-001
     0   Back
2 -> 1   Add Course            -> Course added successfully. ID: CR-001
     3   Activate Course       -> enter CR-001 -> Course activated successfully
     0   Back
3 -> 1   Enroll Student        -> enter STU-001, CR-001 -> Enrollment successful. ID: ENROLL-001
     2   View Enrollments
     3   Complete Enrollment   -> enter ENROLL-001 -> Enrollment completed successfully.
```

Invalid menu input is re-prompted (`Enter a valid number:`) rather than throwing, and any
validation or lookup failure is reported inline:

```
Enter student ID: STU-999
Error: Student not found
```

---


