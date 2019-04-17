package com.ec.cart.listener;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ec.cart.service.CartService;
import com.ec.common.utils.JsonUtils;

/**
 * 购物车监听器,监听对象为删除购物车商品
 * 监听到动作后调用CartService,去Redis删除购物车
 * @author bystander
 * @date 2018/10/7
 */
@Component
public class CartListener {

    @Autowired
    private CartService cartService;

    @SuppressWarnings("unchecked")
	@RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "ec.cart.delete.queue", durable = "true"),
            exchange = @Exchange(name = "ec.cart.exchange",
                    type = ExchangeTypes.TOPIC,
                    ignoreDeclarationExceptions = "true"),
            key = {"cart.delete"}
    ))
    public void listenDelete(String params) {
        Map<String, Object> map = JsonUtils.toMap(params, String.class, Object.class);
        List<Object> ids = (List<Object>) map.get("skuIds");
        Integer userId = (Integer) map.get("userId");
        cartService.deleteCarts(ids, userId);
    }
}
