package com.tt.jms.producer;

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
@ContextConfiguration(value = "/producer.xml")
public class ProducerServiceTest {
    private ApplicationContext context = null;
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("producer.xml");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendMessage() throws Exception {
        ProducerService producerService = context.getBean(ProducerService.class);
        for (int i = 0; i <100 ; i++) {
            producerService.sendMessage("test"+i);
        }
    }

}