package com.ec.commodity.service;

import java.util.List;

import com.ec.commodity.pojo.Brand;
import com.ec.commodity.pojo.Category;
import com.ec.commodity.vo.BrandVo;
import com.ec.common.vo.PageResult;

/**
 * @author bystander
 * @date 2018/9/15
 */
public interface BrandService {

    PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key);

    void saveBrand(Brand brand, List<Long> cids);

    List<Category> queryCategoryByBid(Long bid);

    void updateBrand(BrandVo brandVo);

    void deleteBrand(Long bid);

    List<Brand> queryBrandByCid(Long cid);

    Brand queryBrandByBid(Long id);

    List<Brand> queryBrandByIds(List<Long> ids);

}
