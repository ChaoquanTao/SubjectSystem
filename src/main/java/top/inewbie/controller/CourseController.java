package top.inewbie.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.Global;
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

    private static final Logger logger = Logger.getLogger(String.valueOf(CourseController.class));
    /**
     * 查询所有课程信息和选课信息
     * 从redis中读取所有课程信息和选课记录然后返回给前端
     */
    @RequestMapping(value = "/getAllCourses",method = RequestMethod.POST)
    public @ResponseBody List getAllCourses(@RequestBody String token){
        System.out.println("传过来的token:"+token);
        logger.info("传过来的："+token);
        System.out.println(new JWTTokenUtil().getUserNameFromeToke(token));

        List<Course> courseList = new ArrayList() ;
        //先从course_id_set中拿出所有的course id
        Set<String> courseIds = redisTemplate.opsForSet().members(Global.COURSE_ID_SET) ;
        System.out.println(courseIds);
        for (String id:
             courseIds) {
            Map<String,Object> courseEntry = redisTemplate.opsForHash().entries(id) ;
            courseList.add(new Course(String.valueOf(id),
                    String.valueOf(courseEntry.get(Global.COURSE_ENTRY.COURSE_NAME)),
                    String.valueOf(courseEntry.get(Global.COURSE_ENTRY.TEACHER_NAME)),
                    Integer.valueOf((Integer) courseEntry.get(Global.COURSE_ENTRY.CLASSROOM_CAPACITY)))) ;
        }
        System.out.println(courseList);
        return courseList ;
    }


}
