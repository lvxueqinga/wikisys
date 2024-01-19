package com.tailai.mywiki.model;
import lombok.Data;

/**
 * book表结构
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:13
 */
@Data
public class Book {
    private Integer id;
    private String name;
    private String category;
    private String pic;
}
