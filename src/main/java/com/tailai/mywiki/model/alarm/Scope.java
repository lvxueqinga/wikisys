package com.tailai.mywiki.model.alarm;

import lombok.Data;

@Data
public class Scope {
    Integer scope_id;
    String scope_name;
    String brand;
    Boolean is_monitor;
    Boolean is_alarm;


}
