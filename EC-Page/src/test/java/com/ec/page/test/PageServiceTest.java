package com.ec.page.test;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ec.page.service.PageService;

/**
 * @author bystander
 * @date 2018/9/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PageServiceTest {
	
	@Autowired
	private PageService pageService;

	@Test
	public void createHtml() {
		pageService.createHtml(156L);
	}
}