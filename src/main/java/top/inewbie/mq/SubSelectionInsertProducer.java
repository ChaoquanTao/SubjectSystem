package top.inewbie.mq;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.inewbie.pojo.SelectedCourse;

public class SubSelectionInsertProducer {
    private ApplicationContext container;
    private String topic ;
    private String userId ;
    private String courseId ;

    public SubSelectionInsertProducer(String topic, String userID, String courseId) {
        this.container = new ClassPathXmlApplicationContext(
                "classpath:producer.xml");
        this.topic = topic ;
        this.userId = userID ;
        this.courseId = courseId ;
    }

    public void sendMessage() throws Exception {
        SpringProducer producer = container.getBean(SpringProducer.class);

        System.out.println("producer发送信息中。。。");
            //创建一条消息对象，指定其主题、标签和消息内容
            Message msg = new Message(
                    topic,
                    null,
                    JSON.toJSONString(new SelectedCourse(userId,courseId)).getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
            );
        System.out.println("信息已经发送，等待接收");
            //发送消息并返回结果
            SendResult sendResult = producer.getProducer().send(msg);

//            System.out.printf("%s%n", sendResult);
            System.out.println(sendResult);

    }
}
