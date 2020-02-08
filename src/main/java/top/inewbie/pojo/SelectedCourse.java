package top.inewbie.pojo;

public class SelectedCourse {
    private String stuId ;
    private String courseId ;
    private String courseName ;
    private String courseTeacher ;
    private int courseCapacity ;

    public SelectedCourse(String stuId, String courseId) {
        this.stuId = stuId;
        this.courseId = courseId;
    }

    public SelectedCourse(String stuId, String courseId, String courseName, String courseTeacher, int courseCapacity) {
        this.stuId = stuId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseTeacher = courseTeacher;
        this.courseCapacity = courseCapacity;
    }

    public String getStuId() {
        return stuId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }
}
