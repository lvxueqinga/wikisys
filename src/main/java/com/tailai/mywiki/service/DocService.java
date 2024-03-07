package com.tailai.mywiki.service;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Doc;
import com.tailai.mywiki.resp.PageResp;

import javax.validation.Valid;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/03/07 17:48
 */
public interface DocService {

    //查询内容
    PageResp<JSONObject> searchDoc(@Valid Doc doc);

    //编辑
    PageResp<JSONObject> editDoc(Doc doc);


}
