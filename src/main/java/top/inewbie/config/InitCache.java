package top.inewbie.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import top.inewbie.pojo.Course;
import top.inewbie.service.CourseService;

import java.util.List;

/**
 * 从数据库中读取课程信息，缓存到redis中
 * redis中：课程信息，选课信息。
 */
@Component(value = "test")
public class InitCache implements InitializingBean {

    @Autowired
    CourseService courseService ;

    @Autowired
    RedisTemplate redisTemplate ;

    public void afterPropertiesSet() throws Exception {
        System.out.println("ddddddddddddddddddd");
        System.out.println(redisTemplate);

        /**
         * 从数据库读取所有课程信息
         */
        List<Course> courses = courseService.getAllCourses();

        System.out.println(courses);
        /**
         * 将课程信息写入redis
         */
        for (Course course:
             courses) {

            redisTemplate.opsForHash().put(course.getCourseId(),"name",course.getCourseName());
            redisTemplate.opsForHash().put(course.getCourseId(),"teacher",course.getCourseTeacher());
            redisTemplate.opsForHash().put(course.getCourseId(),"capacity",course.getCourseCapacity());
        }

        System.out.println(redisTemplate.opsForHash().entries(courses.get(0).getCourseId())) ;

    }
}