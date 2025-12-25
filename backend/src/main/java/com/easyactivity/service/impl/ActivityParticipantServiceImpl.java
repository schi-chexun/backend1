package com.easyactivity.service.impl;

import com.easyactivity.entity.Activity;
import com.easyactivity.entity.ActivityParticipant;
import com.easyactivity.mapper.ActivityMapper;
import com.easyactivity.mapper.ActivityParticipantMapper;
import com.easyactivity.service.ActivityParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动参与者服务实现类
 */
@Service
public class ActivityParticipantServiceImpl implements ActivityParticipantService {
    
    @Autowired
    private ActivityParticipantMapper participantMapper;
    
    @Autowired
    private ActivityMapper activityMapper;
    
    @Override
    @Transactional
    public ActivityParticipant register(Long activityId, Long userId) {
        // 检查活动是否存在
        Activity activity = activityMapper.selectById(activityId);
        if (activity == null) {
            throw new RuntimeException("活动不存在");
        }
        
        // 检查是否已报名
        ActivityParticipant existing = participantMapper.selectByActivityIdAndUserId(activityId, userId);
        if (existing != null) {
            throw new RuntimeException("您已报名此活动");
        }
        
        // 检查是否已满员
        if (activity.getMaxParticipants() != null && 
            activity.getCurrentParticipants() >= activity.getMaxParticipants()) {
            throw new RuntimeException("活动已满员");
        }
        
        // 创建报名记录
        ActivityParticipant participant = new ActivityParticipant();
        participant.setActivityId(activityId);
        participant.setUserId(userId);
        participant.setRegisterTime(LocalDateTime.now());
        participant.setCheckInStatus(0); // 未签到
        participantMapper.insert(participant);
        
        // 更新活动参与人数
        activity.setCurrentParticipants(activity.getCurrentParticipants() + 1);
        activityMapper.updateParticipants(activityId, activity.getCurrentParticipants());
        
        return participant;
    }
    
    @Override
    @Transactional
    public void cancel(Long activityId, Long userId) {
        ActivityParticipant participant = participantMapper.selectByActivityIdAndUserId(activityId, userId);
        if (participant == null) {
            throw new RuntimeException("未找到报名记录");
        }
        
        participantMapper.deleteById(participant.getId());
        
        // 更新活动参与人数
        Activity activity = activityMapper.selectById(activityId);
        if (activity != null) {
            activity.setCurrentParticipants(Math.max(0, activity.getCurrentParticipants() - 1));
            activityMapper.updateParticipants(activityId, activity.getCurrentParticipants());
        }
    }
    
    @Override
    @Transactional
    public void checkIn(Long activityId, Long userId) {
        ActivityParticipant participant = participantMapper.selectByActivityIdAndUserId(activityId, userId);
        if (participant == null) {
            throw new RuntimeException("未找到报名记录");
        }
        
        if (participant.getCheckInStatus() == 1) {
            throw new RuntimeException("您已签到");
        }
        
        participant.setCheckInStatus(1);
        participant.setCheckInTime(LocalDateTime.now());
        participantMapper.update(participant);
    }
    
    @Override
    public List<ActivityParticipant> getParticipantsByActivityId(Long activityId) {
        return participantMapper.selectByActivityId(activityId);
    }
    
    @Override
    public List<ActivityParticipant> getActivitiesByUserId(Long userId) {
        return participantMapper.selectByUserId(userId);
    }

    // ====================================================================
    // 扩展功能：统计分析
    // ====================================================================

    @Override
    public Double calculateParticipationRate(Long activityId) {
        Activity activity = activityMapper.selectById(activityId);
        if (activity == null || activity.getMaxParticipants() == 0) {
            return 0.0;
        }
        return (double) activity.getCurrentParticipants() / activity.getMaxParticipants() * 100;
    }

    @Override
    public Long countUserParticipations(Long userId) {
        return (long) participantMapper.selectByUserId(userId).size();
    }

    @Override
    public Double calculateUserCheckInRate(Long userId) {
        List<ActivityParticipant> participations = participantMapper.selectByUserId(userId);
        if (participations.isEmpty()) {
            return 0.0;
        }
        long checkedIn = participations.stream()
                .filter(p -> p.getCheckInStatus() == 1)
                .count();
        return (double) checkedIn / participations.size() * 100;
    }
}

