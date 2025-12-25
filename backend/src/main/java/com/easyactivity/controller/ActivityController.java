package com.easyactivity.controller;

import com.easyactivity.common.Result;
import com.easyactivity.dto.ActivityPublishDTO;
import com.easyactivity.entity.Activity;
import com.easyactivity.service.ActivityService;
import com.easyactivity.vo.ActivityVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 活动控制器
 */
@RestController
@RequestMapping("/activities")
public class ActivityController {
    
    @Autowired
    private ActivityService activityService;
    
    /**
     * 获取所有活动
     */
    @GetMapping
    public Result<List<ActivityVO>> getAll() {
        List<Activity> activities = activityService.getAll();
        List<ActivityVO> activityVOs = activities.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return Result.success(activityVOs);
    }
    
    /**
     * 根据ID获取活动
     */
    @GetMapping("/{id}")
    public Result<ActivityVO> getById(@PathVariable Long id) {
        Activity activity = activityService.getById(id);
        if (activity == null) {
            return Result.error("活动不存在");
        }
        return Result.success(convertToVO(activity));
    }
    
    /**
     * 根据状态获取活动列表
     */
    @GetMapping("/status/{status}")
    public Result<List<ActivityVO>> getByStatus(@PathVariable Integer status) {
        List<Activity> activities = activityService.getByStatus(status);
        List<ActivityVO> activityVOs = activities.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return Result.success(activityVOs);
    }
    
    /**
     * 创建活动
     */
    @PostMapping
    public Result<ActivityVO> create(@RequestBody ActivityPublishDTO dto) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(dto, activity);
        Activity created = activityService.create(activity);
        return Result.success("活动创建成功", convertToVO(created));
    }
    
    /**
     * 更新活动
     */
    @PutMapping("/{id}")
    public Result<ActivityVO> update(@PathVariable Long id, @RequestBody ActivityPublishDTO dto) {
        Activity activity = new Activity();
        BeanUtils.copyProperties(dto, activity);
        activity.setId(id);
        Activity updated = activityService.update(activity);
        return Result.success("活动更新成功", convertToVO(updated));
    }
    
    /**
     * 删除活动
     */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        activityService.delete(id);
        return Result.success("活动删除成功");
    }
    
    /**
     * 更新活动状态
     */
    @PutMapping("/{id}/status")
    public Result<?> updateStatus(@PathVariable Long id) {
        activityService.updateActivityStatus(id);
        return Result.success("活动状态更新成功");
    }
    
    /**
     * 转换为VO
     */
    private ActivityVO convertToVO(Activity activity) {
        ActivityVO vo = new ActivityVO();
        BeanUtils.copyProperties(activity, vo);
        return vo;
    }

    /**
     * 根据关键词搜索活动
     */
    @GetMapping("/search")
    public Result<List<ActivityVO>> searchActivities(@RequestParam String keyword) {
        List<Activity> activities = activityService.searchActivities(keyword);
        List<ActivityVO> activityVOs = activities.stream()
                .map(this::convertToVO)
                .collect(Collectors. toList());
        return Result. success(activityVOs);
    }

    /**
     * 根据地点查询活动
     */
    @GetMapping("/location")
    public Result<List<ActivityVO>> getActivitiesByLocation(@RequestParam String location) {
        List<Activity> activities = activityService.getActivitiesByLocation(location);
        List<ActivityVO> activityVOs = activities.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return Result.success(activityVOs);
    }

    /**
     * 根据创建者查询活动
     */
    @GetMapping("/creator/{creatorId}")
    public Result<List<ActivityVO>> getActivitiesByCreator(@PathVariable Long creatorId) {
        List<Activity> activities = activityService.getActivitiesByCreator(creatorId);
        List<ActivityVO> activityVOs = activities.stream()
                .map(this::convertToVO)
                .collect(Collectors. toList());
        return Result. success(activityVOs);
    }

    /**
     * 获取推荐活动
     */
    @GetMapping("/recommended")
    public Result<List<ActivityVO>> getRecommendedActivities(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Activity> activities = activityService.getRecommendedActivities(limit);
        List<ActivityVO> activityVOs = activities.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        return Result.success(activityVOs);
    }
}
