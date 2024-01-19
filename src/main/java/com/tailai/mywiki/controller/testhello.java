package com.tailai.mywiki.controller;


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
public class testhello {

    @Value("${test.hello}")
    private String testhello ;
    @RequestMapping("/hello")
    public String hello(){

        System.out.print(testhello);
        return "hello world";
    }
}
