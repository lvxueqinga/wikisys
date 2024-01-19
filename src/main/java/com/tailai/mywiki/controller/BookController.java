package com.tailai.mywiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:21
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/search")
    public List<JSONObject> search(){
        return  bookService.searchBook();
    }

}
