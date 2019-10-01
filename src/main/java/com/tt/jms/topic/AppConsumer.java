package com.tt.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  消息消费者（主题模式）
 *  主题中的消费被所有的订阅者进行消费
 *  在主题模式下，如果订阅者提前没有订阅发布者，则无法获取发布者已经发布的消息；
 *  也就是订阅者需要提前绑定发布者的相关信息才可以消费发布者的消息，即订阅者提前运行；
 *   订阅者提前订阅了发布者的消息，然后多个订阅在消费消息时能够消费发布者的每一条
 *   消息，这也是和队列模式的最大的不同。
 *
 */
public class AppConsumer {
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
//        5、创建一个目标
        Destination destination = session.createTopic(topicName);
//        6、创建一个生产者
        MessageConsumer consumer = session.createConsumer(destination);
//     7、创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    //如果有多个消费者的话，我们是平均消费了消息(主题模式)
                    System.out.println("接受消息："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
//        8、关闭连接(接受消息需要将连接打开)
//        connection.close();
    }
}
