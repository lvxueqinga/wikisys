package com.tailai.mywiki.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tailai.mywiki.config.MywikiApplication;
import com.tailai.mywiki.dao.BookMapper;
import com.tailai.mywiki.req.BookReq;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:20
 */
@Service
public class BookServiceImpl implements BookService {
    private static final Logger LOG = LoggerFactory.getLogger(MywikiApplication.class);

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageResp<JSONObject> searchBook(BookReq req) {

        PageHelper.startPage(req.getPage(),req.getSize());
        List<JSONObject> ans = bookMapper.searchBook(req);

        PageInfo<JSONObject> pageInfo = new PageInfo<>(ans);
        LOG.info("总行数 {}",pageInfo.getTotal());
        LOG.info("总页数 {}",pageInfo.getPages());

        PageResp<JSONObject> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ans);

        return pageResp;
    }
}
