package com.tailai.mywiki.dao.alarm;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.req.alarm.ScopeReq;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ScopeMapper {
    //查询
    List<JSONObject> searchScope(ScopeReq req);


    //搜索
    List<JSONObject> searchScopepara(ScopeReq req);
}
