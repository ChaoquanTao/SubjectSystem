package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.inewbie.mq.SubSelectionInsertConsumer;
import top.inewbie.pojo.MsgTopic;

public class ConsumeTest {

    public static void main(String[] args) {
//        ApplicationContext ac=new ClassPathXmlApplicationContext
//                ("classpath:/spring/spring-dao.xml");
        try {
            new SubSelectionInsertConsumer(MsgTopic.SUBJECT_SELECTION_INSERTION).
                    consume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
