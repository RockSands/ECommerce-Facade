package com.ec.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.ec.search.pojo.Goods;

/**
 * @author bystander
 * @date 2018/9/22
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long> {
}
