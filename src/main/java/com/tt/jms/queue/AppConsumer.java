package com.tt.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *  消息消费者
 */
public class AppConsumer {
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
        MessageConsumer consumer = session.createConsumer(destination);
//     7、创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage= (TextMessage) message;
                try {
                    //如果有多个消费者的话，我们是平均消费了消息
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
