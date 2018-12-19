package com.ec.commodity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ec.commodity.mapper.CategoryMapper;
import com.ec.commodity.pojo.Category;
import com.ec.commodity.service.CategoryService;
import com.ec.common.enums.ExceptionEnum;
import com.ec.common.exception.ECException;

/**
 * @author bystander
 * @date 2018/9/15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> queryCategoryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        if (CollectionUtils.isEmpty(categoryList)) {
            throw new ECException(ExceptionEnum.CATEGORY_NOT_FOUND);
        }
        return categoryList;
    }

}
