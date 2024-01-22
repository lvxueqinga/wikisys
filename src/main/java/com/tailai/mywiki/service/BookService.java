package com.tailai.mywiki.service;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.req.BookReq;
import com.tailai.mywiki.resp.PageResp;

import java.util.List;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:15
 */
public interface BookService {

    //查询全部
    PageResp<JSONObject> searchBook(BookReq req);
}
