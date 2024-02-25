package com.tailai.mywiki.model;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * book表结构
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/02/25 21:43
 */
@Data
public class Category {
    private Integer id;
    private Integer parent;
    private Integer sort;
    private String name;

}
