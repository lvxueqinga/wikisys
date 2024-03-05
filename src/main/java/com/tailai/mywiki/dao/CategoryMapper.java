package com.tailai.mywiki.dao;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Category;
import com.tailai.mywiki.req.CategoryReq;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:48
 */
@Repository
public interface CategoryMapper {
    //查询全部
    List<JSONObject> searchCategory();

    //编辑分类
    int editCategory(Category category);

    //新增分类
    int addCategory(Category category);

    //删除分类
    int delCategory(Category category);

    //搜索分类
    List<JSONObject> searchCategorypara(CategoryReq req);
}
