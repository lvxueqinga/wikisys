package com.tailai.mywiki.req.alarm;
import com.tailai.mywiki.req.PageReq;
import lombok.Data;

/**
 *
 * @program：mywiki
 * @author: lvxueqing
 * @create: 2024/01/19 15:58
 */
@Data
public class ScopeReq extends PageReq {
    private Integer scope_id;
    private String scope_name;
    private String brand;
    private Boolean is_monitor;
    private Boolean is_alarm;
}
