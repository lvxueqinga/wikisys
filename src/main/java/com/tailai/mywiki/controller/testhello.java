package com.tailai.mywiki.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 14:42
 *
 * 测试hello接口
 * http://localhost:8080/hello
 */
@RestController
@Api(value = "/",description = "测试模块接口")
public class testhello {

    @Value("${test.hello}")
    private String testhello ;
    @RequestMapping("/hello")
    @ApiOperation(value = "hello",httpMethod = "GET")
    public String hello(){
        System.out.print(testhello);
        return "hello world";
    }
}
