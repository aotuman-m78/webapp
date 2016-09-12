package org.zero.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by rfang on 2016/9/12.
 */
public class MessageConsumer implements MessageListener {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message) {
        log.info("received in scheduler message : {}", message);
    }
}
