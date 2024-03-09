package com.tailai.mywiki.service.alarm;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.model.alarm.Scope;
import com.tailai.mywiki.req.alarm.ScopeReq;
import com.tailai.mywiki.resp.PageResp;


public interface ScopeService {

    //查询全部
    PageResp<JSONObject> searchScope(ScopeReq req);



    //查询
    PageResp<JSONObject> searchScopewithName(ScopeReq req);
}
