package com.tailai.mywiki.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tailai.mywiki.config.MywikiApplication;
import com.tailai.mywiki.dao.DocMapper;
import com.tailai.mywiki.model.Doc;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:54
 */
@Service
public class DocServiceImpl implements DocService {
    private static final Logger LOG = LoggerFactory.getLogger(MywikiApplication.class);

    @Autowired
    private DocMapper docMapper;

    @Override
    public String searchDoc(Doc doc) {


        String ans = docMapper.searchDoc(doc);

        return ans;
    }

    @Override
    public PageResp<JSONObject> editDoc(Doc doc) {
        PageResp<JSONObject> pageResp = new PageResp<>();
        int ans;

            ans = docMapper.editDoc(doc);

        if (ans == 0){
            pageResp.setSuccess(false);
        }else {
            pageResp.setSuccess(true);
        }

        return pageResp;
    }




}
