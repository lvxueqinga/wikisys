package com.tailai.mywiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Book;
import com.tailai.mywiki.req.BookReq;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:21
 */
@RestController
@RequestMapping("/book")
@Api(value = "/",description = "图书管理模块接口")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/search")
    @ApiOperation(value = "查询列表",httpMethod = "GET")
    public PageResp<JSONObject> search(BookReq req){
        return  bookService.searchBook(req);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑",httpMethod = "POST")
    public int search(@RequestBody Book book){
        return  bookService.editBook(book);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增",httpMethod = "POST")
    public int add(@RequestBody Book book){
        return  bookService.addBook(book);
    }

}
