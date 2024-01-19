package com.tailai.mywiki.controller;


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

    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
