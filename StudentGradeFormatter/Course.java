package StudentGradeFormatter;

public class Course {
    private String courseCode;
    private float test1;
    private float test2;
    private float test3;
    private float exam;
    
    public Course(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Course code can't be null or empty");
        }
        
        this.courseCode = courseCode;
        this.test1 = 0.0f;
        this.test2 = 0.0f;
        this.test3 = 0.0f;
        this.exam = 0.0f;
    }
    
    // Getters
    public String getCourseCode() {
        return courseCode;
    }
    
    public float getTest1() {
        return test1;
    }
    
    public float getTest2() {
        return test2;
    }
    
    public float getTest3() {
        return test3;
    }

    public float getExam() {
        return exam;
    }

    // Setters
    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.isEmpty()) {
            throw new IllegalArgumentException("Course code can't be null or empty");
        }
        this.courseCode = courseCode;
    }
    
    public void setTest1(float test1) {
        if (test1 < 0 || test1 > 100) {
            throw new IllegalArgumentException("Grade should be between 0 and 100");
        }
        this.test1 = test1;
    }
    
    public void setTest2(float test2) {
        if (test2 < 0 || test2 > 100) {
            throw new IllegalArgumentException("Grade should be between 0 and 100");
        }
        this.test2 = test2;
    }

    public void setTest3(float test3) {
        if (test3 < 0 || test3 > 100) {
            throw new IllegalArgumentException("Grade should be between 0 and 100");
        }
        this.test3 = test3;
    }

    public void setExam(float exam) {
        if (exam < 0 || exam > 100) {
            throw new IllegalArgumentException("Grade should be between 0 and 100");
        }
        this.exam = exam;
    }
    
    public float calculateFinalGrade() {
        return (test1 * 0.2f) + (test2 * 0.2f) + (test3 * 0.2f) + (exam * 0.4f);
    }
}