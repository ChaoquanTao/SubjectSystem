import top.inewbie.dao.UserMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.inewbie.pojo.User;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext
                ("spring/spring-dao.xml");

        UserMapper mapper = (UserMapper) applicationContext.getBean(UserMapper.class);
        System.out.println(mapper);
        User user = mapper.getUser("tao");
        System.out.println(user.getPassWord());
    }
}
