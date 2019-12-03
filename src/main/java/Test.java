import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.inewbie.config.InitCache;
import top.inewbie.config.RedisConfig;
import top.inewbie.dao.CourseMapper;
import top.inewbie.dao.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.User;
import top.inewbie.service.CourseService;
import top.inewbie.service.impl.CourseServiceImpl;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext
                ("spring/spring-dao.xml");

        UserMapper mapper = (UserMapper) applicationContext.getBean(UserMapper.class);
        System.out.println(mapper);
        User user = mapper.getUser("tao");
        System.out.println(user.getPassWord());

//        applicationContext.getBean(InitCache.class) ;


//        CourseMapper courseService = applicationContext.getBean(CourseMapper.class);
//        RedisTemplate redisTemplate = (RedisTemplate) applicationContext.getBean("redisTemplate");
//        System.out.println(courseService);
//        List<Course> courses = courseService.getAllCourses();
//        for (Course course:
//                courses) {
//
//            redisTemplate.opsForHash().put(course.getCourseId(),"name",course.getCourseName());
//            redisTemplate.opsForHash().put(course.getCourseId(),"teacher",course.getCourseTeacher());
//            redisTemplate.opsForHash().put(course.getCourseId(),"capacity",course.getCourseCapacity());
//        }
    }
}
