package com.ec.commodity.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 对SPU的一对一，对SPU的纵向切分。 将大字段分离，优化开销
 * @author bystander
 * @date 2018/9/18
 */
@Table(name = "tb_spu_detail")
@Data
public class SpuDetail {

    @Id
    private Long spuId;

    //商品描述
    private String description;

    //通用规格参数数据,所有的参数Json格式
    private String genericSpec;

    //特殊规格参数数据,所有的参数Json格式
    private String specialSpec;

    //包装清单
    private String packingList;

    //售后服务
    private String afterService;
}
