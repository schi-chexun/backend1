package com.easyactivity.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 活动发布DTO
 */
@Data
public class ActivityPublishDTO {
    private String title;
    private String description;
    private String location;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxParticipants;
    private Long creatorId;
}

