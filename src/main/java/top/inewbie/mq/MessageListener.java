package top.inewbie.mq;

import com.alibaba.fastjson.JSON;

import org.apache.log4j.Logger;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import top.inewbie.executor.SqlExecutor;
import top.inewbie.pojo.SelectedCourse;
import java.util.List;

public class MessageListener implements MessageListenerConcurrently {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SqlExecutor sqlExecutor ;

    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//        sqlExecutor = new SqlExecutor();
        if (list != null) {
            for (MessageExt ext : list) {
                /**
                 * 这里我应该使用工厂模式得到每条消息应该被处理的方法
                 */
                System.out.println("sqlExecutor:"+sqlExecutor);
                sqlExecutor.execute(ext);

                System.out.println("监听到消息 : " +
                        ((SelectedCourse)JSON.parseObject(ext.getBody(), SelectedCourse.class)).getCourseId());
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

