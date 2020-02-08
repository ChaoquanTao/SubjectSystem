package top.inewbie.mq;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import top.inewbie.pojo.SelectedCourse;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MessageListener implements MessageListenerConcurrently {

    private Logger logger = Logger.getLogger(getClass());

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (list != null) {
            for (MessageExt ext : list) {
                System.out.println("监听到消息 : " +
                        ((SelectedCourse)JSON.parseObject(ext.getBody(), SelectedCourse.class)).getCourseId());
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}

