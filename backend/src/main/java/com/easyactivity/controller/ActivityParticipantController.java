package com.easyactivity.controller;

import com.easyactivity.common.Result;
import com.easyactivity.entity.ActivityParticipant;
import com.easyactivity.service.ActivityParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 活动参与者控制器
 */
@RestController
@RequestMapping("/participants")
public class ActivityParticipantController {
    
    @Autowired
    private ActivityParticipantService participantService;
    
    /**
     * 报名参加活动
     */
    @PostMapping("/register")
    public Result<ActivityParticipant> register(@RequestParam Long activityId, 
                                                 @RequestParam Long userId) {
        ActivityParticipant participant = participantService.register(activityId, userId);
        return Result.success("报名成功", participant);
    }
    
    /**
     * 取消报名
     */
    @DeleteMapping("/cancel")
    public Result<?> cancel(@RequestParam Long activityId, @RequestParam Long userId) {
        participantService.cancel(activityId, userId);
        return Result.success("取消报名成功");
    }
    
    /**
     * 签到
     */
    @PostMapping("/checkin")
    public Result<?> checkIn(@RequestParam Long activityId, @RequestParam Long userId) {
        participantService.checkIn(activityId, userId);
        return Result.success("签到成功");
    }
    
    /**
     * 获取活动的参与者列表
     */
    @GetMapping("/activity/{activityId}")
    public Result<List<ActivityParticipant>> getByActivityId(@PathVariable Long activityId) {
        List<ActivityParticipant> participants = participantService.getParticipantsByActivityId(activityId);
        return Result.success(participants);
    }
    
    /**
     * 获取用户参与的活动列表
     */
    @GetMapping("/user/{userId}")
    public Result<List<ActivityParticipant>> getByUserId(@PathVariable Long userId) {
        List<ActivityParticipant> participants = participantService.getActivitiesByUserId(userId);
        return Result.success(participants);
    }

    /**
     * 计算活动参与率
     */
    @GetMapping("/participation-rate/{activityId}")
    public Result<Double> calculateParticipationRate(@PathVariable Long activityId) {
        Double rate = participantService.calculateParticipationRate(activityId);
        return Result.success(rate);
    }

    /**
     * 统计用户参与活动总数
     */
    @GetMapping("/user-participations/{userId}")
    public Result<Long> countUserParticipations(@PathVariable Long userId) {
        Long count = participantService.countUserParticipations(userId);
        return Result.success(count);
    }

    /**
     * 计算用户签到率
     */
    @GetMapping("/user-checkin-rate/{userId}")
    public Result<Double> calculateUserCheckInRate(@PathVariable Long userId) {
        Double rate = participantService.calculateUserCheckInRate(userId);
        return Result. success(rate);
    }
}








