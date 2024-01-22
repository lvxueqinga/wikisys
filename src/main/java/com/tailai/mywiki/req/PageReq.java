package com.tailai.mywiki.req;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/22 14:08
 */
@Data
public class PageReq {
    private int page;
    private int size;
}
