package top.inewbie.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component(value = "test")
public class InitCache implements InitializingBean {

    public void afterPropertiesSet() throws Exception {
        System.out.println("ddddddddddddddddddd");
    }
}