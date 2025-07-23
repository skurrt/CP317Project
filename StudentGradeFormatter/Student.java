package StudentGradeFormatter;
import java.util.*;

public class Student implements Comparable<Student> {
    private int studentId;
    private String name;
    private ArrayList<Course> courses;
    
    public Student(int studentId, String name) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID should be positive");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        
        this.studentId = studentId;
        this.name = name;
        this.courses = new ArrayList<>();
    }
    
    // Getters
    public int getStudentId() {
        return studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    // Setters
    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Student ID should be positive");
        }
        this.studentId = studentId;
    }
    
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty");
        }
        this.name = name;
    }
    
    public void addCourse(Course course) {
        if (course == null) {
            throw new IllegalArgumentException("Course can't be null");
        }
        courses.add(course);
    }
    
    public Course getCourse(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
    
    public int compareTo(Student other) {
        return Integer.compare(this.studentId, other.studentId);
    }
}