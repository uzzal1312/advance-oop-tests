package com.example.studentmanagement;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class StudentService {
    private List<Student> students = new ArrayList<>();
    private Set<Integer> idSet = new HashSet<>();

    public StudentService() {
        loadStudentsFromCSV();
    }

    private void loadStudentsFromCSV() {
        String csvFile = "/workspaces/advance-oop-tests/lab-test-two/students.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                if (data.length == 3) {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    double cgpa = Double.parseDouble(data[2].trim());
                    if (!idSet.contains(id)) {
                        students.add(new Student(id, name, cgpa));
                        idSet.add(id);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> getStudentsSortedByCgpa() {
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(new CgpaComparator());
        return sorted;
    }

    public boolean removeStudentById(int id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == id) {
                iterator.remove();
                idSet.remove(id);
                return true;
            }
        }
        return false;
    }
}