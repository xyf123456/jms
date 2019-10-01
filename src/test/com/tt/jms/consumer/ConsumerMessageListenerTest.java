package com.tt.jms.consumer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(value = "/consumer.xml")
public class ConsumerMessageListenerTest {
    ApplicationContext context = null;
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("consumer.xml");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onMessage1() throws Exception {
        ConsumerMessageListener  consumerMessageListener1
                = (ConsumerMessageListener) context.getBean("consumerMessageListener");
        ConsumerMessageListener  consumerMessageListener2
                = (ConsumerMessageListener) context.getBean("consumerMessageListener");
    }

}