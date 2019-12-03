package top.inewbie.dao;

import org.springframework.stereotype.Repository;
import top.inewbie.pojo.Course;

import java.util.List;

@Repository
public interface CourseMapper {
    List<Course> getAllCourses() ;
}
