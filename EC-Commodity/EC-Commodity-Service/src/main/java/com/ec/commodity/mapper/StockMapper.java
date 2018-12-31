package com.ec.commodity.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.ec.commodity.pojo.Stock;
import com.ec.common.mapper.BaseBatchMapper;

/**
 * @author bystander
 * @date 2018/9/18
 */
public interface StockMapper extends BaseBatchMapper<Stock, Long> {

    @Update("update tb_stock set stock = stock - #{num} where sku_id = #{skuId} and stock >= #{num}")
    int decreaseStock(@Param("skuId") Long skuId, @Param("num") Integer num);
}
