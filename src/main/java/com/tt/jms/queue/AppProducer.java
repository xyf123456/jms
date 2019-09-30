package com.tt.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  消息生产者（队列模式）
 */
public class AppProducer {
//    61616是activemq的默认的端口号
    private static final String url="tcp://192.168.1.101:61616";
//    创建队列的名称
    private static final String queueName="queue-test";

    public static void main(String[] args) throws JMSException {
//        1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        2、创建Connection
        Connection connection = connectionFactory.createConnection();
//        3、启动连接
        connection.start();
//        4、创建会话,是否事物进行处理
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        5、创建一个目标
        Destination destination = session.createQueue(queueName);
//        6、创建一个生产者
        MessageProducer producer = session.createProducer(destination);
//        7、创建消息
        for (int i = 0; i <100 ; i++) {
            TextMessage textMessage=session.createTextMessage("test"+i);
//            8、生产者发送消息
            producer.send(textMessage);
            System.out.println("发送消息："+textMessage.getText());
        }
//        9、关闭连接
        connection.close();
    }
}
