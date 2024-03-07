package com.tailai.mywiki.model;
import lombok.Data;

/**
 * Doc表结构
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/03/07 17:47
 */
@Data
public class Doc {
    private Integer id;
    private Integer ebookid;
    private Integer view_count;
    private String content;
    private String name;

}
