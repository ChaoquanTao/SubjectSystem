package top.inewbie.mq;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.inewbie.pojo.SelectedCourse;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class SpringProducer {

    private Logger logger = Logger.getLogger(getClass());

    @Value("spring_producer_group")
    private String producerGroupName;

    @Value("localhost:9876")
    private String nameServerAddr;

    private DefaultMQProducer producer;

    public SpringProducer() {
    }

    public SpringProducer(String producerGroupName, String nameServerAddr) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        this.producerGroupName = producerGroupName;
        this.nameServerAddr = nameServerAddr;
    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("开始启动消息生产者服务...");

        //创建一个消息生产者，并设置一个消息生产者组
        try {
            producer = new DefaultMQProducer(producerGroupName);
        }catch (Exception e){

        }
        //指定 NameServer 地址
        producer.setNamesrvAddr(nameServerAddr);
        //初始化 SpringProducer，整个应用生命周期内只需要初始化一次
        producer.start();

        System.out.println("消息生产者服务启动成功.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("开始关闭消息生产者服务...");

        producer.shutdown();

        System.out.println("消息生产者服务已关闭.");
    }

    public DefaultMQProducer getProducer() {
        return producer;
    }

    public void sendMessage(String topic,String userId, String courseId) throws Exception {
//      producer = container.getBean(SpringProducer.class);

        System.out.println("producer发送信息中。。。");
        //创建一条消息对象，指定其主题、标签和消息内容
        Message msg = new Message(
                topic,
                null,
                JSON.toJSONString(new SelectedCourse(userId,courseId)).getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
        );

        //发送消息并返回结果
        SendResult sendResult = producer.send(msg);
        System.out.println("信息已经发送，等待接收");
//            System.out.printf("%s%n", sendResult);
        System.out.println("sendresult:"+sendResult);

    }
}



