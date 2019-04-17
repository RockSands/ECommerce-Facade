package com.ec.common.vo;

import com.ec.common.enums.ExceptionEnum;

import lombok.Data;

/**
 * 自定义异常结果类
 * @author bystander
 * @date 2018/9/15
 */

@Data
public class ExceptionResult {

    private int status;

    private String message;

    private long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status = em.value();
        this.message = em.message();
        this.timestamp = System.currentTimeMillis();
    }
}
