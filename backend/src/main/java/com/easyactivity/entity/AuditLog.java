package com.easyactivity. entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 审核记录实体类
 */
@Data
public class AuditLog {

    /**
     * 审核记录ID
     */
    private Long id;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 审核人ID
     */
    private Long auditorId;

    /**
     * 审核状态：0-待审核，1-已通过，2-已拒绝
     */
    private Integer auditStatus;

    /**
     * 审核意见
     */
    private String auditComment;

    /**
     * 审核时间
     */
    private LocalDateTime auditTime;

    // 关联查询字段（不对应数据库字段）
    /**
     * 审核人姓名
     */
    private String auditorName;

    /**
     * 活动标题
     */
    private String activityTitle;
}