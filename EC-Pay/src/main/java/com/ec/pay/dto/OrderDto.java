package com.ec.pay.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.ec.commodity.dto.CartDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotNull
    private Long addressId; // 收获人地址id
    @NotNull
    private Integer paymentType;// 付款类型
    @NotNull
    private List<CartDto> carts;// 订单详情
}