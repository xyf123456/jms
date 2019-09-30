package com.tt.jms.producer;

import javax.jms.Message;

/**
 * 生产者接口
 */
public interface ProducerService {

    void sendMessage(String message);
}
