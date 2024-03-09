package com.tailai.mywiki.model.alarm;

import lombok.Data;

@Data
public class AlarmRecord {
    Integer scope_id;
    String scope_name;
    String brand;
    String day;
    Integer hour;
    String dev_status;
    String dev_name;
    String create_time;

}
