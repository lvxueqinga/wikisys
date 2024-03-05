package com.tailai.mywiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Category;
import com.tailai.mywiki.req.CategoryReq;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:40
 */
@RestController
@RequestMapping("/category")
@Api(value = "/",description = "图书管理模块接口")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/search")
    @ApiOperation(value = "查询列表",httpMethod = "GET")
    public PageResp<JSONObject> search(){
        return  categoryService.searchCategory();
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑",httpMethod = "POST")
    public PageResp<JSONObject> search(@Valid @RequestBody Category category){
        return  categoryService.editCategory(category);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增",httpMethod = "POST")
    public PageResp<JSONObject> add(@Valid @RequestBody Category category){
        return  categoryService.addCategory(category);
    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除",httpMethod = "POST")
    public PageResp<JSONObject> del(@RequestBody Category category){
        return  categoryService.delCategory(category);
    }

    @PostMapping("/searchpara")
    @ApiOperation(value = "查询列表",httpMethod = "POST")
    public PageResp<JSONObject> searchpara(@RequestBody CategoryReq req){
        return  categoryService.searchCategorywithName(req);
    }

    @GetMapping("/get/name")
    @ApiOperation(value = "查询分类名称",httpMethod = "GET")
    public String searchpara(int id){
        return  categoryService.getCategoryName(id);
    }



}
