# Lab Test Two: Student Management System - Implementation Documentation

## Overview
This document details the implementation of the Student Management System as per the lab test requirements. The system is built using Spring Boot and Java Collections Framework to manage student data loaded from a CSV file.

## Project Structure
```
student-management-system/
├── pom.xml
└── src/main/java/com/example/studentmanagement/
    ├── Student.java
    ├── CgpaComparator.java
    ├── StudentService.java
    ├── StudentController.java
    └── StudentManagementSystemApplication.java
```

## Implementation Details

### 1. Student Class
- **File**: `Student.java`
- **Purpose**: Represents a student entity with id, name, and cgpa fields
- **Key Features**:
  - Implements `Comparable<Student>` for natural ordering by id (ascending)
  - Provides getters and setters
  - Overrides `toString()` for easy debugging

### 2. CGPA Comparator
- **File**: `CgpaComparator.java`
- **Purpose**: Custom comparator for sorting students by CGPA in descending order
- **Implementation**: Uses `Double.compare(s2.getCgpa(), s1.getCgpa())` for reverse ordering

### 3. Student Service
- **File**: `StudentService.java`
- **Purpose**: Business logic layer for managing student operations
- **Key Features**:
  - Loads students from CSV file on initialization
  - Uses `ArrayList` to store students
  - Uses `HashSet` to track unique IDs and prevent duplicates
  - Provides methods for:
    - Getting all students
    - Getting students sorted by CGPA
    - Removing students by ID using Iterator

### 4. REST Controller
- **File**: `StudentController.java`
- **Purpose**: Exposes REST endpoints for the student management system
- **Endpoints**:
  - `GET /students`: Returns all students
  - `GET /students/sort/cgpa`: Returns students sorted by CGPA (descending)

### 5. Main Application
- **File**: `StudentManagementSystemApplication.java`
- **Purpose**: Spring Boot application entry point

## Task Implementation

### Task 1: Load Students from CSV
- **Implementation**: In `StudentService` constructor
- **Method**: `loadStudentsFromCSV()`
- **Process**:
  1. Reads CSV file line by line using `BufferedReader`
  2. Skips header line
  3. Parses each line: id,name,cgpa
  4. Checks for duplicate IDs using `HashSet`
  5. Adds unique students to `ArrayList`

### Task 2: Sorting
- **Comparable**: `Student` implements `Comparable<Student>` with `compareTo()` method sorting by id ascending
- **Comparator**: `CgpaComparator` class implements `Comparator<Student>` for CGPA descending sort
- **Usage**:
  - For id sorting: `Collections.sort(students)` (natural order)
  - For CGPA sorting: `students.sort(new CgpaComparator())`

### Task 3: Delete Using Iterator
- **Implementation**: `removeStudentById(int id)` method in `StudentService`
- **Process**:
  1. Gets iterator from the students list
  2. Iterates through students
  3. Removes student with matching id using `iterator.remove()`
  4. Updates the idSet to remove the id
  5. Returns true if removed, false otherwise

### Task 4: REST API Exposure
- **Controller**: `StudentController` with `@RestController` annotation
- **Endpoints**:
  - `@GetMapping("/students")`: Returns `studentService.getAllStudents()`
  - `@GetMapping("/students/sort/cgpa")`: Returns `studentService.getStudentsSortedByCgpa()`

## Technologies Used
- **Java**: 17
- **Spring Boot**: 3.2.0
- **Collections Framework**:
  - `ArrayList`: For storing students
  - `HashSet`: For tracking unique IDs
  - `Iterator`: For safe removal during iteration
- **I/O**: `BufferedReader` and `FileReader` for CSV reading

## CSV File Details
- **Location**: `/workspaces/advance-oop-tests/lab-test-two/students.csv`
- **Format**: id,name,cgpa (comma-separated)
- **Sample Data**:
  ```
  id,name,cgpa
  101,Arafat Rahman,3.75
  102,Nusrat Jahan,3.90
  ...
  ```
- **Duplicate Handling**: ID 104 appears twice in CSV but is stored only once

## Testing
- **Build**: `mvn clean compile` - Successful
- **Run**: `mvn spring-boot:run` - Starts on port 8080
- **Endpoints Tested**:
  - `GET /students`: Returns all students as JSON array
  - `GET /students/sort/cgpa`: Returns students sorted by CGPA descending

## Key Learning Points
1. **Collections Usage**: Proper use of ArrayList, HashSet, and Iterator
2. **Comparable vs Comparator**: Understanding natural ordering vs custom sorting
3. **Spring Boot**: Creating REST APIs with minimal configuration
4. **CSV Parsing**: Reading and parsing CSV files in Java
5. **Duplicate Prevention**: Using HashSet for efficient duplicate checking
6. **Iterator Safety**: Safe removal during iteration to avoid ConcurrentModificationException

## Conclusion
The implementation successfully fulfills all lab test requirements using Java Collections Framework and Spring Boot. The system efficiently loads student data, handles duplicates, provides sorting capabilities, and exposes REST endpoints for data access.