import dao.AdministratorMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Administrator;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext
                ("spring/spring-dao.xml");

        AdministratorMapper mapper = (AdministratorMapper) applicationContext.getBean(AdministratorMapper.class);
        Administrator administrator = mapper.getAdministrator("tao");
        System.out.println(administrator.getPassWord());
    }
}
