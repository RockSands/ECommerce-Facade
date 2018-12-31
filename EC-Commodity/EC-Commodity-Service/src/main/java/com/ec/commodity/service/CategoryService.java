package com.ec.commodity.service;

import java.util.List;

import com.ec.commodity.pojo.Category;

/**
 * @author CKW
 * @date 2018/9/15
 */
public interface CategoryService {


    List<Category> queryCategoryByPid(Long pid);

    List<Category> queryCategoryByIds(List<Long> ids);

    List<Category> queryAllByCid3(Long id);

}
