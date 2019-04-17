package com.ec.common.vo;


import java.util.List;

import lombok.Data;

/**
 * 翻页的类
 * @author bystander
 * @date 2018/9/15
 */
@Data
public class PageResult<T> {

    private Long total;
    private Integer totalPage;
    private List<T> items;
    
    /**
     * feign需要
     */
    public PageResult() {
    	
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
