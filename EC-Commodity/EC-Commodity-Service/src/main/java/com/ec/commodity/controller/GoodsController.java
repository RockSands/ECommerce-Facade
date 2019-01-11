package com.ec.commodity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.commodity.dto.CartDto;
import com.ec.commodity.pojo.Sku;
import com.ec.commodity.pojo.Spu;
import com.ec.commodity.pojo.SpuDetail;
import com.ec.commodity.service.GoodsService;
import com.ec.common.vo.PageResult;

/**
 * @author bystander
 * @date 2018/9/18
 */
@RestController
public class GoodsController {


    @Autowired
    private GoodsService goodsService;

    @GetMapping("spu/page")
    public ResponseEntity<PageResult<Spu>> querySpuByPage(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "saleable", required = false)Boolean saleable
    ) {
    	PageResult<Spu> result = goodsService.querySpuByPage(page,rows,key,saleable);
        return ResponseEntity.ok(result);
    }

    /**
     * 查询spu详情
     * @param spuId
     * @return
     */
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId") Long spuId) {
        return ResponseEntity.ok(goodsService.querySpuDetailBySpuId(spuId));
    }

    /**
     * 根据spuId查询商品详情
     * @param id
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(goodsService.querySkuBySpuId(id));

    }

    /**
     * 根据sku ids查询sku
     * @param ids
     * @return
     */
    @GetMapping("sku/list/ids")
    public ResponseEntity<List<Sku>> querySkusByIds(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(goodsService.querySkusByIds(ids));
    }


    /**
     * 删除商品
     * @param spuId
     * @return
     */
    @DeleteMapping("spu/spuId/{spuId}")
    public ResponseEntity<Void> deleteGoodsBySpuId(@PathVariable("spuId") Long spuId) {
        goodsService.deleteGoodsBySpuId(spuId);
        return ResponseEntity.ok().build();
    }


    /**
     * 添加商品
     * @param spu
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> addGoods(@RequestBody Spu spu) {
        goodsService.addGoods(spu);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 更新商品
     * @param spu
     * @return
     */
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody Spu spu) {
        goodsService.updateGoods(spu);
        return ResponseEntity.ok().build();
    }

    @PutMapping("spu/saleable")
    public ResponseEntity<Void> handleSaleable(@RequestBody Spu spu) {
        goodsService.handleSaleable(spu);
        return ResponseEntity.ok().build();

    }

    /**
     * 根据spuId查询spu及skus
     * @param spuId
     * @return
     */
    @GetMapping("spu/{id}")
    public ResponseEntity<Spu> querySpuBySpuId(@PathVariable("id") Long spuId) {
        return ResponseEntity.ok(goodsService.querySpuBySpuId(spuId));
    }

    /**
     * 减库存
     * @param cartDtos
     * @return
     */
    @PostMapping("stock/decrease")
    public ResponseEntity<Void> decreaseStock(@RequestBody List<CartDto> cartDtos){
        goodsService.decreaseStock(cartDtos);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
