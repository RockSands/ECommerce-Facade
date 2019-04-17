package com.ec.page.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ec.page.service.PageService;

/**
 * 对商品进行监听,一旦商品属性/描述变更,会自动调用PageService构建静态页面
 * @author bystander
 * @date 2018/9/28
 */
@Component
public class ItemListener {

	@Autowired
	private PageService pageService;

	@RabbitListener(bindings = @QueueBinding(value = @Queue(name = "ecommerce.page.insert.queue", durable = "true"), exchange = @Exchange(value = "ecommerce.commodity.exchange", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"), key = {
			"item.insert", "item.update" }))
	public void listenInsert(Long id) {
		if (id != null) {
			// 新增或修改
			pageService.createHtml(id);
		}
	}

	@RabbitListener(bindings = @QueueBinding(value = @Queue(name = "ecommerce.page.delete.queue", durable = "true"), exchange = @Exchange(value = "ecommerce.commodity.exchange", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"), key = {
			"item.delete" }))
	public void listenDelete(Long id) {
		if (id != null) {
			// 删除
			pageService.deleteHtml(id);
		}
	}
}
