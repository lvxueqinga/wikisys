package com.tailai.mywiki.req;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:58
 */
@Data
public class BookReq extends PageReq{
    private String name;
    private String category;
}
