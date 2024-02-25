package com.tailai.mywiki.req;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 41:45
 */
@Data
public class CategoryReq extends PageReq{

    private Integer parent;
    private Integer sort;
    private String name;
}
