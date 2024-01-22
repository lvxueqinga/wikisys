package com.tailai.mywiki.dao;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.req.BookReq;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:15
 */
@Repository
public interface BookMapper {
    //查询全部
    List<JSONObject> searchBook(BookReq req);
}
