package com.ec.commodity.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ec.commodity.pojo.Brand;

/**
 * 与Service一致, 目的被其他项目引用,实现服务调用
 * @author bystander
 * @date 2018/9/22
 */
public interface BrandApi {

    @GetMapping("brand/{id}")
    Brand queryById(@PathVariable("id") Long id);

    @GetMapping("brand/list")
    List<Brand> queryBrandsByIds(@RequestParam("ids") List<Long> ids);
}
