package top.inewbie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.inewbie.dao.CourseMapper;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.SelectedCourse;
import top.inewbie.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper ;

    public List<Course> getAllCourses() {
        List res = courseMapper.getAllCourses() ;
        return res;
    }

    public List<SelectedCourse> getSelectedCourses() {
        List<SelectedCourse> res = courseMapper.getSelectedCourses();
        return  res ;
    }
}
