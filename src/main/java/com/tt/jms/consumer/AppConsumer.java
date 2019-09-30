package com.tt.jms.consumer;

import com.tt.jms.producer.ProducerService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;

/**
 *  消息消费者（队列模式）
 */
public class AppConsumer {

    public static void main(String[] args) throws JMSException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
    }
}
