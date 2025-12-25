package com.easyactivity.common.enums;

/**
 * 活动状态枚举
 */
public enum ActivityStatusEnum {
    NOT_STARTED(0, "未开始"),
    IN_PROGRESS(1, "进行中"),
    ENDED(2, "已结束"),
    CANCELLED(3, "已取消");
    
    private final Integer code;
    private final String desc;
    
    ActivityStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
}

