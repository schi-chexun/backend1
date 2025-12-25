package com.easyactivity.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 活动实体类
 */
@Data
public class Activity {
    /**
     * 活动ID
     */
    private Long id;
    
    /**
     * 活动标题
     */
    private String title;
    
    /**
     * 活动描述
     */
    private String description;
    
    /**
     * 活动地点
     */
    private String location;
    
    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;
    
    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;
    
    /**
     * 活动状态：0-未开始，1-进行中，2-已结束，3-已取消
     */
    private Integer status;
    
    /**
     * 最大参与人数
     */
    private Integer maxParticipants;
    
    /**
     * 当前参与人数
     */
    private Integer currentParticipants;
    
    /**
     * 创建者ID
     */
    private Long creatorId;
    
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 审核状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer auditStatus;
}








