package com.tailai.mywiki.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.tailai.mywiki.dao.BookMapper;
import com.tailai.mywiki.service.BookService;
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
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<JSONObject> searchBook() {
        return bookMapper.searchBook();
    }
}
