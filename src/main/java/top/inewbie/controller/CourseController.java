package top.inewbie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.inewbie.pojo.AllCourses;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.Global;
import top.inewbie.service.CourseService;
import top.inewbie.util.JWTTokenUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import static com.mysql.cj.conf.PropertyKey.logger;

@Controller
public class CourseController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    CourseService courseService ;

    private static final Logger logger = Logger.getLogger(String.valueOf(CourseController.class));
    /**
     * 查询所有课程信息和选课信息
     * 从redis中读取所有课程信息和选课记录然后返回给前端
     */
    @RequestMapping(value = "/getAllCourses",method = RequestMethod.POST)
    public @ResponseBody
    AllCourses getAllCourses(@RequestBody String token){
        AllCourses courses = courseService.getAllCoursesFromRedis(token);
        return courses ;
    }


}
