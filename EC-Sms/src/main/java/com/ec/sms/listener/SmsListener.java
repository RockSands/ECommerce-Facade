package com.ec.sms.listener;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ec.sms.properties.SmsProperties;
import com.ec.sms.utils.SmsUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author bystander
 * @date 2018/9/29
 */
@Slf4j
@Component
@EnableConfigurationProperties(value = SmsProperties.class)
public class SmsListener {

    @Autowired
    private SmsProperties props;

    @Autowired
    private SmsUtil smsUtil;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "ecommerce.sms.verify.queue"),
            exchange = @Exchange(name = "ecommerce.sms.exchange", type = ExchangeTypes.TOPIC),
            key = "sms.verify.code"
    ))
    public void listenVerifyCode(Map<String, Object> msg) {
        if (msg == null) {
            return;
        }
        String phone = (String) msg.remove("phone");
        if (StringUtils.isBlank(phone)) {
            return;
        }
        log.debug("短信发送信息:" + msg);
        smsUtil.sendSms(props.getSignName(), props.getVerifyCodeTemplate(), phone, msg);
    }


}
