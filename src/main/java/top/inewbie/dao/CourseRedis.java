package top.inewbie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import top.inewbie.pojo.AllCourses;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.Global;
import top.inewbie.util.JWTTokenUtil;

import java.util.*;

@Repository
public class CourseRedis {
    @Autowired
    RedisTemplate redisTemplate ;

    public AllCourses getAllCourses(String token){
        String name = new JWTTokenUtil().getUserNameFromeToke(token);

        List<Course> unSelectedCourseList = new ArrayList() ;
        List<Course> selectedCourseList = new ArrayList<>() ;
        //先从course_id_set中拿出所有的course id
        Set<String> courseIds = redisTemplate.opsForSet().members(Global.COURSE_ID_SET) ;
        Set<String> selectedIds = redisTemplate.opsForSet().members(name) ;

        /**
         * 求所有课和已选课的差集
         */
        Set<String> unSelectedIds = new HashSet<>();
        unSelectedIds.addAll(courseIds) ;
        unSelectedIds.removeAll(selectedIds) ;

        for (String id:
                unSelectedIds) {
            System.out.println(id);
        }
        System.out.println(courseIds);
        /**
         * 得到所有的course list
         */
        constructCourseList(unSelectedIds, unSelectedCourseList) ;
        constructCourseList(selectedIds, selectedCourseList) ;

        /**
         * 得到用户已选的course list
         */

//        System.out.println(courseList);
        return new AllCourses(unSelectedCourseList,selectedCourseList);
    }

    public void constructCourseList(Set<String> ids, List<Course> courses){
        for (String id:
                ids) {
            Map<String,Object> courseEntry = redisTemplate.opsForHash().entries(id) ;
            courses.add(new Course(String.valueOf(id),
                    String.valueOf(courseEntry.get(Global.COURSE_ENTRY.COURSE_NAME)),
                    String.valueOf(courseEntry.get(Global.COURSE_ENTRY.TEACHER_NAME)),
                    Integer.valueOf((Integer) courseEntry.get(Global.COURSE_ENTRY.CLASSROOM_CAPACITY)))) ;
        }
    }

}
