package com.ec.search;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ec.search.client.GoodsClient;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpuClientTest {

	@Autowired
	private GoodsClient goodsClient;

	@Test
	public void testQuerySpu() {
		Object result = goodsClient.querySpuByPage(1, 10, null, true);
		System.out.println(result.getClass().getName());
		System.out.println(result);
	}

}