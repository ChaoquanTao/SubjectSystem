package top.inewbie.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


public class SubSelectionInsertConsumer {
    private ApplicationContext container;
    private SpringConsumer consumer ;

    public SubSelectionInsertConsumer(String topic) {
        this.container = new ClassPathXmlApplicationContext
                        ("classpath:consumer.xml");
        consumer = container.getBean(SpringConsumer.class) ;
        System.out.println("设置topic");
        consumer.setTopicName(topic);
    }

    public void consume() throws InterruptedException {
//        SpringConsumer consumer = container.getBean(SpringConsumer.class);
//        consumer.getConsumer().subscribe();
        Thread.sleep(200 * 1000);

        consumer.destroy();
    }
}
