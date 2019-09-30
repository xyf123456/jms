package com.tt.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * 生产者接口实现类
 */
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    JmsTemplate jmsTemplate;

   /* @Resource(name = "queueDestination")
    Destination destination;*/

    @Resource(name = "topicDestination")
    Destination destination;

    @Override
    public void sendMessage(final String message) {
                //使用jmsTemplate发送消息
                jmsTemplate.send(destination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
//                创建一个消息
                        TextMessage textMessage = session.createTextMessage(message);
                        return textMessage;
                    }
        });
        System.out.println("发送的消息：" + message);
    }
}
