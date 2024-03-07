package com.tailai.mywiki.req;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 41:45
 */
@Data
public class DocReq extends PageReq{

    private Integer ebookid;
    private Integer id;
    private String name;
}
