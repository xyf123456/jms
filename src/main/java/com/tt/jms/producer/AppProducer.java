package com.tt.jms.producer;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.*;

/**
 *  消息生产者（队列模式）
 */
public class AppProducer {

    public static void main(String[] args) throws JMSException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService producerService = context.getBean(ProducerService.class);

        for (int i = 0; i <100 ; i++) {
            producerService.sendMessage("test"+i);
        }
        context.close();
    }
}
