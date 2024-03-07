package com.tailai.mywiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Doc;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.DocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/03/07 18:00
 */
@RestController
@RequestMapping("/doc")
@Api(value = "/",description = "电子书内容模块接口")
public class DocController {
    @Resource
    private DocService docService;

    @PostMapping("/content")
    @ApiOperation(value = "查询内容",httpMethod = "POST")
    public PageResp<JSONObject> search(@Valid @RequestBody Doc doc){
        System.out.println(doc.getEbookid());
        return  docService.searchDoc(doc);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑内容",httpMethod = "POST")
    public PageResp<JSONObject> edit(@Valid @RequestBody Doc doc){
        return  docService.editDoc(doc);
    }





}
