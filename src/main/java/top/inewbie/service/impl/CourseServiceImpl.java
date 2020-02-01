package top.inewbie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.inewbie.dao.CourseMapper;
import top.inewbie.dao.CourseRedis;
import top.inewbie.pojo.AllCourses;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.SelectedCourse;
import top.inewbie.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseMapper courseMapper ;

    @Autowired
    CourseRedis courseRedis ;

    public List<Course> getAllCourses() {
        List res = courseMapper.getAllCourses() ;
        return res;
    }

    public List<SelectedCourse> getSelectedCourses() {
        List<SelectedCourse> res = courseMapper.getSelectedCourses();
        return  res ;
    }


    @Override
    public AllCourses getAllCoursesFromRedis(String token) {
        AllCourses allCourses = courseRedis.getAllCourses(token) ;
        return allCourses;
    }
}
