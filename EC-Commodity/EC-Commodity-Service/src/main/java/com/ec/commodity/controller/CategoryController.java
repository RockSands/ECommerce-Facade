package com.ec.commodity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.commodity.pojo.Category;
import com.ec.commodity.service.CategoryService;

/**
 * @author CKW
 * @date 2018/9/15
 */
@RestController
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

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

}
