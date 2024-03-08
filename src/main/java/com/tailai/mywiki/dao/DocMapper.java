package com.tailai.mywiki.dao;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.Doc;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:48
 */
@Repository
public interface DocMapper {
    //查询内容
    String searchDoc(Doc doc);

    //编辑内容
    int editDoc(Doc doc);




}
