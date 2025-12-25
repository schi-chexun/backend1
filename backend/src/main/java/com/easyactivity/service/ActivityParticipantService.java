package com.easyactivity.service;

import com.easyactivity.entity.ActivityParticipant;
import java.util.List;

/**
 * 活动参与者服务接口
 */
public interface ActivityParticipantService {
    
    /**
     * 报名参加活动
     */
    ActivityParticipant register(Long activityId, Long userId);
    
    /**
     * 取消报名
     */
    void cancel(Long activityId, Long userId);
    
    /**
     * 签到
     */
    void checkIn(Long activityId, Long userId);
    
    /**
     * 查询活动的参与者列表
     */
    List<ActivityParticipant> getParticipantsByActivityId(Long activityId);
    
    /**
     * 查询用户参与的活动列表
     */
    List<ActivityParticipant> getActivitiesByUserId(Long userId);

    /**
     * 计算活动参与率
     */
    Double calculateParticipationRate(Long activityId);

    /**
     * 统计用户参与活动总数
     */
    Long countUserParticipations(Long userId);

    /**
     * 计算用户签到率
     */
    Double calculateUserCheckInRate(Long userId);
}
