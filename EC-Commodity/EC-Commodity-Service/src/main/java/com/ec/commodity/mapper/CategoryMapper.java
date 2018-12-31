package com.ec.commodity.mapper;

import com.ec.commodity.pojo.Category;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * IdListMapper, 对ID的list操作
 * @author bystander
 * @date 2018/9/15
 */
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category, Long>  { 
}
