package com.tailai.mywiki.resp;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 16:00
 */
@Data
public class BookResp {
    private Integer id;
    private String name;
    private String category;
    private String pic;
}
