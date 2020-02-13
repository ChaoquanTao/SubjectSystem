package top.inewbie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import top.inewbie.dao.CourseMapper;
import top.inewbie.dao.CourseRedis;
import top.inewbie.pojo.AllCourses;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.SelectedCourse;
import top.inewbie.pojo.SubmittedCourse;
import top.inewbie.service.CourseService;

import java.util.List;
import java.util.Set;

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

    /**
     * 向redis中写入提交的选课信息
     * @param submittedCourse
     * @return
     */
    @Override
    public boolean submitCourse(SubmittedCourse submittedCourse) {
        String userName = submittedCourse.getUserName() ;
        Set<String> selectedCourses = submittedCourse.getAdded() ;
        Set<String> deletedCourses = submittedCourse.getDeleted() ;

        for (String courseId : selectedCourses){
            courseRedis.addCourse(userName,courseId) ;
        }

        for (String courseId:
             deletedCourses) {
            courseRedis.deleteCourse(userName,courseId) ;
        }

        return false;
    }

    @Override
    public void addCourseSelection(String userId, String courseId) {
        System.out.println("in courseServiceImpl");
        String res = courseMapper.insertSelectedCourse(userId,courseId);
        System.out.println("插入后返回值："+res);
    }
}
