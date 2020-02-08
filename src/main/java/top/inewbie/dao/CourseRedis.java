package top.inewbie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import top.inewbie.pojo.AllCourses;
import top.inewbie.pojo.Course;
import top.inewbie.pojo.Global;
import top.inewbie.util.JWTTokenUtil;

import java.io.*;
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
                    (Integer) courseEntry.get(Global.COURSE_ENTRY.CLASSROOM_CAPACITY))) ;
        }
    }

    /**
     * 向redis写入选课信息，包括：生成选课信息和减少库存
     */
    public long  addCourse(String userName, String id){
        byte[] sha = null ;
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        byte[] fileBytes = file2byte("add_course.lua") ;
        if(sha==null) {
            sha = jedis.scriptLoad(fileBytes);
            System.out.println(sha);
        }
        Object ojb = jedis.evalsha(sha,2,id.getBytes(),userName.getBytes(),
                id.getBytes()) ;
//        Object ojb = jedis.eval(sha,1,id.getBytes()) ;

//        String script ="local store =   tonumber(redis.call('hget',KEYS[1],'capacity'))\n" +
//                "print(store)\n" +
//                "if store <= 0\n" +
//                "then return 0\n" +
//                "end\n" +
//                "store = store - 1\n" +
//                "redis.call('hset',KEYS[1],'capacity',store)\n" +
//                "redis.call('sadd',KEYS[2],ARGV[1])\n" +
//                "return 1\n" +
//                "\n";
//
//        if(sha==null){
//            sha = jedis.scriptLoad(script) ;
//            System.out.println("sha:"+sha);
//        }
//        Object ojb = jedis.evalsha(sha,2,id,userName,id) ;
//
        long res = (long) ojb;
//        System.out.println(res);
        return res ;
    }

    /**
     * 向redis写入退课信息，包括：删除选课信息和减少库存
     * @param id
     * @return
     */
    public int deleteCourse(String userName, String id){
        return  0 ;
    }

    public byte[] file2byte(String fileName){
//        File file = new File()
        ClassPathResource classPathResource = new ClassPathResource("add_course.lua");

        // 获得File对象，当然也可以获取输入流对象
        try {
            File file = classPathResource.getFile();
            System.out.println(file);

        InputStream is =
                new FileInputStream(file);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte bb[] = new byte[2048] ;
        int ch ;

            ch = is.read(bb) ;
//            System.out.println(is.read());
            while (ch!=-1){
                outputStream.write(bb,0,ch);
                ch = is.read();
            }
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null ;
    }

}