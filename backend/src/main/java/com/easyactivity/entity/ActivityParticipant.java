package com.easyactivity.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 活动参与者实体类
 */
@Data
public class ActivityParticipant {
    /**
     * 主键ID
     */
    private Long id;
    
    /**
     * 活动ID
     */
    private Long activityId;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * 报名时间
     */
    private LocalDateTime registerTime;
    
    /**
     * 签到状态：0-未签到，1-已签到
     */
    private Integer checkInStatus;
    
    /**
     * 签到时间
     */
    private LocalDateTime checkInTime;
}








