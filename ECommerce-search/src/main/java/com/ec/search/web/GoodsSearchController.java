package com.ec.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ec.search.pojo.Goods;
import com.ec.search.pojo.SearchRequest;
import com.ec.search.pojo.SearchResult;
import com.ec.search.service.SearchService;

/**
 * @author bystander
 * @date 2018/9/22
 */
@RestController
public class GoodsSearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("page")
    public ResponseEntity<SearchResult<Goods>> search(@RequestBody SearchRequest searchRequest) {
        return ResponseEntity.ok(searchService.search(searchRequest));
    }
}
