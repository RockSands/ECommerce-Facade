package com.ec.commodity.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ec.commodity.pojo.Brand;
import com.ec.commodity.pojo.Category;

import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author bystander
 * @date 2018/9/16
 */
public interface BrandMapper extends Mapper<Brand>,SelectByIdListMapper<Brand, Long> {

    @Insert("insert into tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
    int saveCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Select("select * from tb_category where id in (select category_id from tb_category_brand where brand_id = #{bid})")
    List<Category> queryCategoryByBid(Long bid);

    @Delete("delete from tb_category_brand where brand_id = #{bid}")
    int deleteCategoryBrandByBid(Long bid);

    @Select("select * from tb_brand where id in (select brand_id from tb_category_brand where category_id = #{cid})")
    List<Brand> queryBrandByCid(Long cid);

}
