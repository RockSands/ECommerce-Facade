package com.ec.page.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ec.page.service.PageService;

/**
 * controller,在静态页面失效时.调用
 * @author bystander
 * @date 2018/9/26
 */
@Controller
public class ECPageController {

    @Autowired
    private PageService pageService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id") Long spuId, Model model) {
        Map<String, Object> attributes = pageService.loadModel(spuId);
        model.addAllAttributes(attributes);
        return "item";

    }

}
