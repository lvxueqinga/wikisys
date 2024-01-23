package com.tailai.mywiki.model;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message = "名称不能为空")
    @Size(max=50 , message = "名称不能超过50字")
    @NotEmpty(message = "名称不能为空")
    private String name;
    private String category;
    private String pic;
}
