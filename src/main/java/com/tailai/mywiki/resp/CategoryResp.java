package com.tailai.mywiki.resp;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 16:00
 */
@Data
public class CategoryResp {
    private Integer id;
    private Integer parent;
    private Integer sort;
    private String name;
}
