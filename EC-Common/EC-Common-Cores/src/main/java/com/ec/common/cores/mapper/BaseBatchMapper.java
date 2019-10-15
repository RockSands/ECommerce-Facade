package com.ec.common.cores.mapper;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 自定义Mapper,
 * 注意@RegisterMapper必须声明才可以生效
 * @author bystander
 * @date 2018/9/19
 */
@RegisterMapper
public interface BaseBatchMapper<T, PK> extends Mapper<T>, IdListMapper<T, PK>, InsertListMapper<T> {
}
