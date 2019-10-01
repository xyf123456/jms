package com.tt.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  消息生产者（主题模式）
 *  主题模式下，与队列模式不同，必须要先预定好消费者，再进行生产消息，这样才能在消费者中接受消息
 *  多个消费者能同时接受主题模式生产出来的所有消息，但是队列模式只能平均消费
 *
 */
public class AppProducer {
//    61616是activemq的默认的端口号
    private static final String url="tcp://192.168.0.102:61616";
//    创建队列的名称
    private static final String topicName="topic-test";

    public static void main(String[] args) throws JMSException {
//        1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
//        2、创建Connection
        Connection connection = connectionFactory.createConnection();
//        3、启动连接
        connection.start();
//        4、创建会话,是否事物进行处理
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
//        5、创建一个目标(主题)
        Destination destination = session.createTopic(topicName);
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
