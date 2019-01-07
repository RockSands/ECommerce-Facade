package com.ec.commodity.service;

import java.util.List;

import com.ec.commodity.dto.CartDto;
import com.ec.commodity.pojo.Sku;
import com.ec.commodity.pojo.Spu;
import com.ec.commodity.pojo.SpuDetail;
import com.ec.common.vo.PageResult;

/**
 * @author bystander
 * @date 2018/9/18
 */
public interface GoodsService {
	PageResult<Spu> querySpuByPage(Integer page, Integer rows, String key, Boolean saleable);

	SpuDetail querySpuDetailBySpuId(Long spuId);

	List<Sku> querySkuBySpuId(Long spuId);

	void deleteGoodsBySpuId(Long spuId);

	void addGoods(Spu spu);

	void updateGoods(Spu spu);

	void handleSaleable(Spu spu);

	Spu querySpuBySpuId(Long spuId);

	List<Sku> querySkusByIds(List<Long> ids);

	void decreaseStock(List<CartDto> cartDtos);

}
