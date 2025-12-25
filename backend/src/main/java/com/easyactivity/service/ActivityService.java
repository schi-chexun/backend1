package com.easyactivity.service;

import com.easyactivity. entity.Activity;
import java. util.List;

/**
 * 活动服务接口
 */
public interface ActivityService {

    /**
     * 根据ID查询活动
     */
    Activity getById(Long id);

    /**
     * 查询所有活动
     */
    List<Activity> getAll();

    /**
     * 根据状态查询活动
     */
    List<Activity> getByStatus(Integer status);

    /**
     * 创建活动
     */
    Activity create(Activity activity);

    /**
     * 更新活动
     */
    Activity update(Activity activity);

    /**
     * 删除活动
     */
    void delete(Long id);

    /**
     * 更新活动状态
     */
    void updateActivityStatus(Long id);

    // ====== 扩展功能（新增）======

    /**
     * 根据关键词搜索活动
     */
    List<Activity> searchActivities(String keyword);

    /**
     * 根据地点查询活动
     */
    List<Activity> getActivitiesByLocation(String location);

    /**
     * 根据创建者查询活动
     */
    List<Activity> getActivitiesByCreator(Long creatorId);

    /**
     * 获取推荐活动
     */
    List<Activity> getRecommendedActivities(Integer limit);
}