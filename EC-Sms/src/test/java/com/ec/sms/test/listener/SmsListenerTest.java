package com.ec.sms.test.listener;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author bystander
 * @date 2018/9/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsListenerTest {


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void listenVerifyCode() throws InterruptedException {
        Map<String,String> map = new HashMap<>();
        map.put("phone", "15121037897");
        map.put("code", "ILoveYou");
        amqpTemplate.convertAndSend("ly.sms.exchange", "sms.verify.code", map);
        /*
         * 避免直接关闭
         */
        Thread.sleep(5000);
    }
}