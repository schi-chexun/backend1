package com.easyactivity.service.impl;

import com.easyactivity.entity.Activity;
import com.easyactivity.mapper.ActivityMapper;
import com.easyactivity.mapper.ActivityParticipantMapper;
import com.easyactivity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 活动服务实现类
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    
    @Autowired
    private ActivityMapper activityMapper;
    
    @Autowired
    private ActivityParticipantMapper participantMapper;
    
    @Override
    public Activity getById(Long id) {
        return activityMapper.selectById(id);
    }
    
    @Override
    public List<Activity> getAll() {
        return activityMapper.selectAll();
    }
    
    @Override
    public List<Activity> getByStatus(Integer status) {
        return activityMapper.selectByStatus(status);
    }
    
    @Override
    @Transactional
    public Activity create(Activity activity) {
        activity.setStatus(0); // 默认未开始
        activity.setCurrentParticipants(0);
        activity.setCreateTime(LocalDateTime.now());
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.insert(activity);
        return activity;
    }
    
    @Override
    @Transactional
    public Activity update(Activity activity) {
        activity.setUpdateTime(LocalDateTime.now());
        activityMapper.update(activity);
        return activityMapper.selectById(activity.getId());
    }
    
    @Override
    @Transactional
    public void delete(Long id) {
        // 删除活动前先删除参与者记录
        List<com.easyactivity.entity.ActivityParticipant> participants = 
            participantMapper.selectByActivityId(id);
        for (com.easyactivity.entity.ActivityParticipant participant : participants) {
            participantMapper.deleteById(participant.getId());
        }
        activityMapper.deleteById(id);
    }
    
    @Override
    @Transactional
    public void updateActivityStatus(Long id) {
        Activity activity = activityMapper.selectById(id);
        if (activity == null) {
            return;
        }
        
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(activity.getStartTime())) {
            activity.setStatus(0); // 未开始
        } else if (now.isAfter(activity.getEndTime())) {
            activity.setStatus(2); // 已结束
        } else {
            activity.setStatus(1); // 进行中
        }
        
        activity.setUpdateTime(now);
        activityMapper.update(activity);
    }

    // ====================================================================
    // 扩展功能：活动搜索、分类、推荐
    // ====================================================================

    @Override
    public List<Activity> searchActivities(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return activityMapper.selectAll();
        }
        return activityMapper.selectByKeyword(keyword);
    }

    @Override
    public List<Activity> getActivitiesByLocation(String location) {
        return activityMapper.selectByLocation(location);
    }

    @Override
    public List<Activity> getActivitiesByCreator(Long creatorId) {
        return activityMapper.selectByCreatorId(creatorId);
    }

    @Override
    public List<Activity> getRecommendedActivities(Integer limit) {
        List<Activity> all = activityMapper.selectAll();
        return all.stream()
                .filter(a -> a.getCurrentParticipants() < a.getMaxParticipants())
                .sorted((a1, a2) -> a2.getCurrentParticipants().compareTo(a1.getCurrentParticipants()))
                .limit(limit == null ? 10 : limit)
                .collect(java.util.stream.Collectors. toList());
    }
}

