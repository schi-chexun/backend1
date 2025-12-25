package com.easyactivity.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 活动视图对象
 */
@Data
public class ActivityVO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private Integer maxParticipants;
    private Integer currentParticipants;
    private Long creatorId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

