package test;



import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.inewbie.mq.SpringConsumer;

public class SpringConsumerTest {

    private ApplicationContext container;

    @Before
    public void setup() {
        container = new ClassPathXmlApplicationContext
                ("classpath:consumer.xml");
    }

    @Test
    public void consume() throws Exception {
        SpringConsumer consumer = container.getBean(SpringConsumer.class);
//        consumer.getConsumer().subscribe();
        Thread.sleep(200 * 1000);

        consumer.destroy();
    }
}

