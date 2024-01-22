package com.tailai.mywiki.resp;
import lombok.Data;

import java.util.List;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/22 14:08
 */
@Data
public class PageResp<T> {
    private long total;
    private Boolean success=true;
    private List<T> list;
}
