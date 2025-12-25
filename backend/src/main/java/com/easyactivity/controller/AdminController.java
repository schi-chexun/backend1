package com.easyactivity.controller;

import com.easyactivity. common.Result;
import com. easyactivity.entity.Activity;
import com.easyactivity.entity.AuditLog;
import com. easyactivity.entity.User;
import com.easyactivity.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // ====================================================================
    // 用户管理
    // ====================================================================

    /**
     * 查询所有用户
     */
    @GetMapping("/users")
    public Result<List<User>> getAllUsers() {
        List<User> users = adminService.getAllUsers();
        return Result.success(users);
    }

    /**
     * 根据角色查询用户
     */
    @GetMapping("/users/role/{role}")
    public Result<List<User>> getUsersByRole(@PathVariable Integer role) {
        List<User> users = adminService.getUsersByRole(role);
        return Result.success(users);
    }

    /**
     * 更新用户角色
     */
    @PutMapping("/users/{userId}/role")
    public Result<? > updateUserRole(@PathVariable Long userId, @RequestParam Integer role) {
        adminService.updateUserRole(userId, role);
        return Result.success("用户角色更新成功");
    }

    /**
     * 批量删除用户
     */
    @DeleteMapping("/users/batch")
    public Result<?> batchDeleteUsers(@RequestBody List<Long> userIds) {
        adminService. batchDeleteUsers(userIds);
        return Result.success("批量删除用户成功");
    }


    // ====================================================================
    // 活动审核
    // ====================================================================

    /**
     * 查询所有活动（含创建者信息）
     */
    @GetMapping("/activities")
    public Result<List<Activity>> getAllActivities() {
        List<Activity> activities = adminService.getAllActivitiesWithCreator();
        return Result.success(activities);
    }

    /**
     * 根据审核状态查询活动
     */
    @GetMapping("/activities/audit-status/{status}")
    public Result<List<Activity>> getActivitiesByAuditStatus(@PathVariable Integer status) {
        List<Activity> activities = adminService.getActivitiesByAuditStatus(status);
        return Result.success(activities);
    }

    /**
     * 审核活动
     */
    @PostMapping("/activities/{activityId}/audit")
    public Result<?> auditActivity(
            @PathVariable Long activityId,
            @RequestParam Integer auditStatus,
            @RequestParam Long auditorId,
            @RequestParam(required = false) String auditComment) {
        adminService.auditActivity(activityId, auditStatus, auditorId, auditComment);
        return Result.success("活动审核成功");
    }

    /**
     * 批量删除活动
     */
    @DeleteMapping("/activities/batch")
    public Result<?> batchDeleteActivities(@RequestBody List<Long> activityIds) {
        adminService.batchDeleteActivities(activityIds);
        return Result.success("批量删除活动成功");
    }

    /**
     * 强制删除活动（含报名记录）
     */
    @DeleteMapping("/activities/{activityId}/force")
    public Result<?> forceDeleteActivity(@PathVariable Long activityId) {
        adminService.forceDeleteActivity(activityId);
        return Result.success("强制删除活动成功");
    }


    // ====================================================================
    // 审核记录
    // ====================================================================

    /**
     * 查询活动的审核历史
     */
    @GetMapping("/audit-logs/activity/{activityId}")
    public Result<List<AuditLog>> getAuditLogsByActivityId(@PathVariable Long activityId) {
        List<AuditLog> logs = adminService. getAuditLogsByActivityId(activityId);
        return Result.success(logs);
    }

    /**
     * 查询所有审核记录
     */
    @GetMapping("/audit-logs")
    public Result<List<AuditLog>> getAllAuditLogs(
            @RequestParam(defaultValue = "100") Integer limit) {
        List<AuditLog> logs = adminService.getAllAuditLogs(limit);
        return Result.success(logs);
    }


    // ====================================================================
    // 基础统计
    // ====================================================================

    /**
     * 统计总用户数
     */
    @GetMapping("/stats/total-users")
    public Result<Long> countTotalUsers() {
        Long count = adminService.countTotalUsers();
        return Result.success(count);
    }

    /**
     * 统计总活动数
     */
    @GetMapping("/stats/total-activities")
    public Result<Long> countTotalActivities() {
        Long count = adminService. countTotalActivities();
        return Result.success(count);
    }

    /**
     * 统计总报名数
     */
    @GetMapping("/stats/total-participations")
    public Result<Long> countTotalParticipations() {
        Long count = adminService.countTotalParticipations();
        return Result.success(count);
    }

    /**
     * 统计待审核活动数
     */
    @GetMapping("/stats/pending-audits")
    public Result<Long> countPendingAudits() {
        Long count = adminService.countPendingAudits();
        return Result.success(count);
    }

    /**
     * 统计今日新增用户数
     */
    @GetMapping("/stats/today-new-users")
    public Result<Long> countTodayNewUsers() {
        Long count = adminService.countTodayNewUsers();
        return Result. success(count);
    }

    /**
     * 统计今日新增活动数
     */
    @GetMapping("/stats/today-new-activities")
    public Result<Long> countTodayNewActivities() {
        Long count = adminService.countTodayNewActivities();
        return Result.success(count);
    }

    /**
     * 统计本周新增用户数
     */
    @GetMapping("/stats/week-new-users")
    public Result<Long> countWeekNewUsers() {
        Long count = adminService.countWeekNewUsers();
        return Result. success(count);
    }

    /**
     * 统计本月新增用户数
     */
    @GetMapping("/stats/month-new-users")
    public Result<Long> countMonthNewUsers() {
        Long count = adminService.countMonthNewUsers();
        return Result.success(count);
    }

    /**
     * 统计进行中的活动数
     */
    @GetMapping("/stats/ongoing-activities")
    public Result<Long> countOngoingActivities() {
        Long count = adminService.countOngoingActivities();
        return Result. success(count);
    }

    /**
     * 统计已结束的活动数
     */
    @GetMapping("/stats/finished-activities")
    public Result<Long> countFinishedActivities() {
        Long count = adminService.countFinishedActivities();
        return Result.success(count);
    }


    // ====================================================================
    // 排行榜/热门数据
    // ====================================================================

    /**
     * 查询热门活动（按报名人数）
     */
    @GetMapping("/stats/popular-activities")
    public Result<List<Activity>> getPopularActivities(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Activity> activities = adminService.getPopularActivities(limit);
        return Result. success(activities);
    }

    /**
     * 查询最近创建的活动
     */
    @GetMapping("/stats/recent-activities")
    public Result<List<Activity>> getRecentActivities(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Activity> activities = adminService. getRecentActivities(limit);
        return Result.success(activities);
    }

    /**
     * 查询最活跃用户
     */
    @GetMapping("/stats/active-users")
    public Result<List<User>> getActiveUsers(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<User> users = adminService.getActiveUsers(limit);
        return Result.success(users);
    }

    /**
     * 计算平均每个活动的报名人数
     */
    @GetMapping("/stats/avg-participants")
    public Result<Double> calculateAvgParticipantsPerActivity() {
        Double avg = adminService.calculateAvgParticipantsPerActivity();
        return Result.success(avg);
    }

    /**
     * 计算活动报名率
     */
    @GetMapping("/stats/fill-rate")
    public Result<Double> calculateActivityFillRate() {
        Double rate = adminService.calculateActivityFillRate();
        return Result. success(rate);
    }


    // ====================================================================
    // 高级统计/分析
    // ====================================================================

    /**
     * 按时间范围统计用户增长
     */
    @GetMapping("/stats/user-growth")
    public Result<List<Map<String, Object>>> getUserGrowthByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Map<String, Object>> data = adminService.getUserGrowthByDateRange(startDate, endDate);
        return Result.success(data);
    }

    /**
     * 按时间范围统计活动增长
     */
    @GetMapping("/stats/activity-growth")
    public Result<List<Map<String, Object>>> getActivityGrowthByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        List<Map<String, Object>> data = adminService. getActivityGrowthByDateRange(startDate, endDate);
        return Result.success(data);
    }

    /**
     * 统计各状态的活动数量
     */
    @GetMapping("/stats/activities-by-status")
    public Result<List<Map<String, Object>>> countActivitiesByStatus() {
        List<Map<String, Object>> data = adminService. countActivitiesByStatus();
        return Result.success(data);
    }

    /**
     * 统计各状态的报名数量
     */
    @GetMapping("/stats/participations-by-status")
    public Result<List<Map<String, Object>>> countParticipationsByStatus() {
        List<Map<String, Object>> data = adminService.countParticipationsByStatus();
        return Result.success(data);
    }

    /**
     * 查询报名人数最多的前N个活动
     */
    @GetMapping("/stats/top-activities")
    public Result<List<Activity>> getTopActivitiesByParticipants(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Activity> activities = adminService.getTopActivitiesByParticipants(limit);
        return Result.success(activities);
    }

    /**
     * 查询创建活动最多的用户
     */
    @GetMapping("/stats/top-creators")
    public Result<List<Map<String, Object>>> getTopCreators(
            @RequestParam(defaultValue = "10") Integer limit) {
        List<Map<String, Object>> data = adminService. getTopCreators(limit);
        return Result.success(data);
    }

    /**
     * 按活动地点统计活动数量
     */
    @GetMapping("/stats/activities-by-location")
    public Result<List<Map<String, Object>>> countActivitiesByLocation() {
        List<Map<String, Object>> data = adminService.countActivitiesByLocation();
        return Result.success(data);
    }

    /**
     * 按月统计活动数量（最近12个月）
     */
    @GetMapping("/stats/activities-by-month")
    public Result<List<Map<String, Object>>> countActivitiesByMonth() {
        List<Map<String, Object>> data = adminService. countActivitiesByMonth();
        return Result.success(data);
    }
}