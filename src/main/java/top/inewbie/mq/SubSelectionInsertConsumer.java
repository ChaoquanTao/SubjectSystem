package top.inewbie.mq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SubSelectionInsertConsumer {
    private ApplicationContext container;

    public SubSelectionInsertConsumer() {
        this.container = new ClassPathXmlApplicationContext
                        ("classpath:consumer.xml");
    }

    public void consume() throws InterruptedException {
        SpringConsumer consumer = container.getBean(SpringConsumer.class);
//        consumer.getConsumer().subscribe();
        Thread.sleep(200 * 1000);

        consumer.destroy();
    }
}
