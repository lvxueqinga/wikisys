package com.tailai.mywiki.controller.alarm;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.alarm.Scope;
import com.tailai.mywiki.req.alarm.ScopeReq;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.alarm.ScopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;



@RestController
@RequestMapping("/scope")
@Api(value = "/",description = "门店管理模块接口")
public class ScopeController {
    @Resource
    private ScopeService ScopeService;

    @GetMapping("/search")
    @ApiOperation(value = "查询列表",httpMethod = "GET")
    public PageResp<JSONObject> search(@Valid ScopeReq req){
        return  ScopeService.searchScope(req);
    }


    @PostMapping("/searchpara")
    @ApiOperation(value = "查询列表",httpMethod = "Post")
    public PageResp<JSONObject> searchpara(@RequestBody  ScopeReq req){
        return  ScopeService.searchScopewithName(req);
    }

}
