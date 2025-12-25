package com.easyactivity.mapper;

import com.easyactivity.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 活动Mapper接口
 */
@Mapper
public interface ActivityMapper {
    
    /**
     * 根据ID查询活动
     */
    Activity selectById(@Param("id") Long id);
    
    /**
     * 查询所有活动
     */
    List<Activity> selectAll();
    
    /**
     * 根据状态查询活动
     */
    List<Activity> selectByStatus(@Param("status") Integer status);
    
    /**
     * 插入活动
     */
    int insert(Activity activity);
    
    /**
     * 更新活动
     */
    int update(Activity activity);
    
    /**
     * 删除活动
     */
    int deleteById(@Param("id") Long id);
    
    /**
     * 更新参与人数
     */
    int updateParticipants(@Param("id") Long id, @Param("currentParticipants") Integer currentParticipants);

    /**
     * 根据关键词搜索活动（标题或描述）
     */
    List<Activity> selectByKeyword(@Param("keyword") String keyword);

    /**
     * 根据地点查询活动
     */
    List<Activity> selectByLocation(@Param("location") String location);

    /**
     * 根据创建者ID查询活动
     */
    List<Activity> selectByCreatorId(@Param("creatorId") Long creatorId);
}








