package com.ec.commodity.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ec.commodity.pojo.SpecGroup;
import com.ec.commodity.pojo.SpecParam;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RequestMapping("spec")
public interface SpecApi {

    // 查询规格参数组，及组内参数
    @GetMapping("{cid}")
    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);

    @GetMapping("params")
    List<SpecParam> querySpecParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "searching", required = false) Boolean searching,
            @RequestParam(value = "generic", required = false) Boolean generic
    );

    @GetMapping("groups/{cid}")
    List<SpecGroup> querySpecGroupByCid(@PathVariable("cid") Long cid);
}
