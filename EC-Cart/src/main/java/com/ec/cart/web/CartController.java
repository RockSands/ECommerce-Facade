package com.ec.cart.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ec.cart.pojo.Cart;
import com.ec.cart.service.CartService;

/**
 * 购物车的Controller
 * @author bystander
 * @date 2018/10/3
 */
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 添加商品到购物车
     *
     * @param cart
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> addCart(@RequestBody Cart cart) {
        cartService.addCart(cart);
        return ResponseEntity.ok().build();
    }


    /**
     * 从购物车中删除商品
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok().build();
    }


    /**
     * 更新购物车中商品的数量
     *
     * @param id  商品ID
     * @param num 修改后的商品数量
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateNum(@RequestParam("id") Long id, @RequestParam("num") Integer num) {
        cartService.updateNum(id, num);
        return ResponseEntity.ok().build();
    }


    /**
     * 查询购物车
     *
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Cart>> listCart() {
        return ResponseEntity.ok(cartService.listCart());
    }

    /**
     * 查询购物车
     *
     * @return
     */
    @GetMapping("count")
    public ResponseEntity<Integer> countCart() {
        return ResponseEntity.ok(cartService.countCart());
    }

}
