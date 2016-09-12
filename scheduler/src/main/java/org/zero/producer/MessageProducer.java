package org.zero.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by rfang on 2016/9/11.
 */
@Component
public class MessageProducer implements InitializingBean {

    @Autowired private AmqpTemplate amqpTemplate;

    private Logger log = LoggerFactory.getLogger(getClass());

    public void sendMessage(Object message) {
        log.info("to send message : {}", message);
        amqpTemplate.convertAndSend("schedulerSendKey", message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("sssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        sendMessage("this is first message");
    }
}
