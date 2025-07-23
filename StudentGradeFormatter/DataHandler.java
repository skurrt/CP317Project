package StudentGradeFormatter;

import java.util.TreeMap;
import java.util.*;
import java.io.*;

public class DataHandler {
    static TreeMap<Integer, Student> students = new TreeMap<>();
    
    // Add a student to the TreeMap
    public static void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Student can't be null");
        }
        students.put(student.getStudentId(), student);
    }
    
    // Get a student by ID
    public static Student getStudent(int studentId) {
        return students.get(studentId);
    }
    
    // Check if a student exists
    public static boolean containsStudent(int studentId) {
        return students.containsKey(studentId);
    }
    
    // Read student names and IDs from file
    public static void readStudentFile(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can't be null or empty");
        }
        
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Student file not found: " + fileName);
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue; 
                }
                
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Invalid format for student file: " + line);
                }
                
                try {
                    int studentId = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    
                    if (containsStudent(studentId)) {
                        throw new IllegalArgumentException("Duplicate student ID: " + studentId);
                    }
                    
                    Student student = new Student(studentId, name);
                    addStudent(student);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid student ID format: " + parts[0]);
                }
            }
        }
        
        if (students.isEmpty()) {
            throw new IllegalArgumentException("No valid student data found in file");
        }
    }
    
    // Read course grades from file and assign to students
    public static void readGradesFile(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
        
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Grades file not found: " + fileName);
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split(",");
                if (parts.length != 6) {
                    throw new IllegalArgumentException("Invalid format in grades file: " + line);
                }
                
                try {
                    int studentId = Integer.parseInt(parts[0].trim());
                    String courseCode = parts[1].trim();
                    float test1 = Float.parseFloat(parts[2].trim());
                    float test2 = Float.parseFloat(parts[3].trim());
                    float test3 = Float.parseFloat(parts[4].trim());
                    float exam = Float.parseFloat(parts[5].trim());
                    
                    Student student = getStudent(studentId);
                    if (student == null) {
                        throw new IllegalArgumentException("Student ID not found: " + studentId);
                    }
                    
                    Course course = new Course(courseCode);
                    course.setTest1(test1);
                    course.setTest2(test2);
                    course.setTest3(test3);
                    course.setExam(exam);
                    
                    student.addCourse(course);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format in grades file: " + line);
                }
            }
        }
    }
    
    // Write formatted output to file
    public static void writeOutputFile(String fileName) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name can't be null or empty");
        }
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Student Grade Report");
            writer.println("====================");
            writer.println();
            
            for (Student student : students.values()) {
                writer.println("Student ID: " + student.getStudentId());
                writer.println("Name: " + student.getName());
                writer.println("Courses:");
                
                for (Course course : student.getCourses()) {
                    writer.printf("  %s - Test1: %.1f, Test2: %.1f, Test3: %.1f, Exam: %.1f, Final: %.1f%n",
                        course.getCourseCode(),
                        course.getTest1(),
                        course.getTest2(),
                        course.getTest3(),
                        course.getExam(),
                        course.calculateFinalGrade());
                }
                writer.println();
            }
        }
    }
