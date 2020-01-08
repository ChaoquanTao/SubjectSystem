package top.inewbie.pojo;

public class Course {
    String courseId ;
    String courseName ;
    String courseTeacher ;
    int courseCapacity ;

    public Course() {
    }

    public Course(String courseId, String courseName, String courseTeacher, int courseCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseCapacity = courseCapacity;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }
}
