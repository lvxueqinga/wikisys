package com.tailai.mywiki.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tailai.mywiki.config.MywikiApplication;
import com.tailai.mywiki.dao.CategoryMapper;
import com.tailai.mywiki.model.Category;
import com.tailai.mywiki.req.CategoryReq;
import com.tailai.mywiki.resp.PageResp;
import com.tailai.mywiki.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;


/**
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:54
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOG = LoggerFactory.getLogger(MywikiApplication.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResp<JSONObject> searchCategory(CategoryReq req) {

        PageHelper.startPage(req.getPage(),req.getSize());
        List<JSONObject> ans = categoryMapper.searchCategory(req);

        PageInfo<JSONObject> pageInfo = new PageInfo<>(ans);
        LOG.info("总行数 {}",pageInfo.getTotal());
        LOG.info("总页数 {}",pageInfo.getPages());

        PageResp<JSONObject> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ans);

        return pageResp;
    }

    @Override
    public PageResp<JSONObject> editCategory(Category category) {
        PageResp<JSONObject> pageResp = new PageResp<>();
        int ans;
        if (ObjectUtils.isEmpty(category.getId())){
            ans = categoryMapper.addCategory(category);
        }else {
            ans = categoryMapper.editCategory(category);
        }
        if (ans == 0){
            pageResp.setSuccess(false);
        }else {
            pageResp.setSuccess(true);
        }

        return pageResp;
    }

     @Override
    public PageResp<JSONObject> addCategory(Category category) {
         PageResp<JSONObject> pageResp = new PageResp<>();
         int ans= categoryMapper.addCategory(category);
         if (ans == 0){
             pageResp.setSuccess(false);
         }else {
             pageResp.setSuccess(true);
         }

         return pageResp;
    }

    @Override
    public PageResp<JSONObject> delCategory(Category category) {
        PageResp<JSONObject> pageResp = new PageResp<>();
        int ans=categoryMapper.delCategory(category);
        if (ans == 0){
            pageResp.setSuccess(false);
        }else {
            pageResp.setSuccess(true);
        }

        return pageResp;

    }

    @Override
    public PageResp<JSONObject> searchCategorywithName(CategoryReq req) {
        PageHelper.startPage(req.getPage(),req.getSize());
        List<JSONObject> ans = categoryMapper.searchCategorypara(req);

        PageInfo<JSONObject> pageInfo = new PageInfo<>(ans);

        PageResp<JSONObject> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(ans);

        return pageResp;
    }


}
