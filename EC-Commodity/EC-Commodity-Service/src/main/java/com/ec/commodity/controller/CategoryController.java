package com.ec.commodity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.commodity.pojo.Category;
import com.ec.commodity.service.BrandService;
import com.ec.commodity.service.CategoryService;

/**
 * Category: 商品的分类
 * @author CKW
 * @date 2018/9/15
 */
@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
    private BrandService brandService;

	/**
	 * 根据父类ID查询分类结果
	 * 
	 * @param pid
	 * @return
	 */
	@GetMapping("list")
	public ResponseEntity<List<Category>> queryCategoryByPid(
			@RequestParam(value = "pid", defaultValue = "0") Long pid) {
		List<Category> categoryList = categoryService.queryCategoryByPid(pid);
		// 简化写法,值得推荐
		return ResponseEntity.ok(categoryList);
	}

	/**
	 * 根据品牌ID查询商品分类
	 *
	 * @param bid
	 * @return
	 */
	@GetMapping("bid/{bid}")
	public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("bid") Long bid) {
		return ResponseEntity.ok(brandService.queryCategoryByBid(bid));
	}

	/**
	 * 根据商品分类Ids查询分类
	 * 
	 * @param ids
	 * @return
	 */
	@GetMapping("list/ids")
	public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids") List<Long> ids) {
		return ResponseEntity.ok(categoryService.queryCategoryByIds(ids));
	}

	/**
	 * 根据cid3查询三级分类
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("all/level")
	public ResponseEntity<List<Category>> queryAllByCid3(@RequestParam("id") Long id) {
		return ResponseEntity.ok(categoryService.queryAllByCid3(id));
	}
}
