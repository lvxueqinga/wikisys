package com.tailai.mywiki.req;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/22 14:08
 */
@Data
public class PageReq {
    @NotNull(message = "页码不能为空")
    private int page;

    @NotNull(message = "每页条数不能为空")
    @Max(value=50 , message = "每页条数不能大于50")
    private int size;
}
