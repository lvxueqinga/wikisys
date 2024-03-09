package com.tailai.mywiki.service.alarm;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tailai.mywiki.config.MywikiApplication;
import com.tailai.mywiki.dao.alarm.ScopeMapper;
import com.tailai.mywiki.model.alarm.Scope;
import com.tailai.mywiki.req.alarm.ScopeReq;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.alarm.ScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ScopeServiceImpl implements ScopeService {
    private static final Logger LOG = LoggerFactory.getLogger(MywikiApplication.class);

    @Autowired
    private ScopeMapper ScopeMapper;


    @Override
    public PageResp<JSONObject> searchScope(ScopeReq req) {

        PageHelper.startPage(req.getPage(),req.getSize());
        List<JSONObject> ans = ScopeMapper.searchScope(req);

        PageInfo<JSONObject> pageInfo = new PageInfo<>(ans);
        LOG.info("总行数 {}",pageInfo.getTotal());
        LOG.info("总页数 {}",pageInfo.getPages());

        PageResp<JSONObject> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ans);

        return pageResp;
    }

    @Override
    public PageResp<JSONObject> searchScopewithName(ScopeReq req) {
        PageHelper.startPage(req.getPage(),req.getSize());
        List<JSONObject> ans = ScopeMapper.searchScopepara(req);

        PageInfo<JSONObject> pageInfo = new PageInfo<>(ans);

        PageResp<JSONObject> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ans);

        return pageResp;
    }


}
