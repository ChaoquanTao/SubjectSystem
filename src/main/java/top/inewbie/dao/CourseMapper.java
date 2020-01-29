package top.inewbie.dao;

import org.springframework.stereotype.Repository;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.SelectedCourse;

import java.util.List;

@Repository
public interface CourseMapper {
    List<Course> getAllCourses() ;
    List<SelectedCourse> getSelectedCourses() ;
}
