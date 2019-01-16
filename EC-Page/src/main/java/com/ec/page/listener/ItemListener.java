package com.ec.page.listener;

import org.springframework.stereotype.Component;

/**
 * @author bystander
 * @date 2018/9/28
 */
@Component
public class ItemListener {

//    @Autowired
//    private PageService pageService;
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "ly.page.insert.queue", durable = "true"),
//            exchange = @Exchange(value = "ly.item.exchange", type = ExchangeTypes.TOPIC,ignoreDeclarationExceptions = "true"),
//            key ={"item.insert", "item.update"}
//    ))
//    public void listenInsert(Long id) {
//        if (id != null) {
//            //新增或修改
//            pageService.createHtml(id);
//        }
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(name = "ly.page.delete.queue", durable = "true"),
//            exchange = @Exchange(value = "ly.item.exchange", type = ExchangeTypes.TOPIC,ignoreDeclarationExceptions = "true"),
//            key ={"item.delete"}
//    ))
//    public void listenDelete(Long id) {
//        if (id != null) {
//            //删除
//            pageService.deleteHtml(id);
//        }
//    }
}
