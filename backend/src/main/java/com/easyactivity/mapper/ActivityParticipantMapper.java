package com.easyactivity.mapper;

import com.easyactivity.entity.ActivityParticipant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 活动参与者Mapper接口
 */
@Mapper
public interface ActivityParticipantMapper {
    
    /**
     * 根据ID查询
     */
    ActivityParticipant selectById(@Param("id") Long id);
    
    /**
     * 根据活动ID查询参与者列表
     */
    List<ActivityParticipant> selectByActivityId(@Param("activityId") Long activityId);
    
    /**
     * 根据用户ID查询参与的活动列表
     */
    List<ActivityParticipant> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 查询用户是否已参与活动
     */
    ActivityParticipant selectByActivityIdAndUserId(@Param("activityId") Long activityId, @Param("userId") Long userId);
    
    /**
     * 插入参与者
     */
    int insert(ActivityParticipant participant);
    
    /**
     * 更新参与者（主要用于签到）
     */
    int update(ActivityParticipant participant);
    
    /**
     * 删除参与者
     */
    int deleteById(@Param("id") Long id);
}








