package top.inewbie.service;

import top.inewbie.pojo.Course;
import top.inewbie.pojo.SelectedCourse;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    List<SelectedCourse> getSelectedCourses() ;
}
