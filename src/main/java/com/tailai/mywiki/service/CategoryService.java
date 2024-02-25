package com.tailai.mywiki.service;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Category;
import com.tailai.mywiki.req.CategoryReq;
import com.tailai.mywiki.resp.PageResp;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:42
 */
public interface CategoryService {

    //查询全部
    PageResp<JSONObject> searchCategory(CategoryReq req);

    //编辑
    PageResp<JSONObject> editCategory(Category category);

    //新增
    PageResp<JSONObject> addCategory(Category category);

    //删除
    PageResp<JSONObject> delCategory(Category category);

    //查询2345
    PageResp<JSONObject> searchCategorywithName(CategoryReq req);
}
