package com.easyactivity.service;

import com.easyactivity. entity.Activity;
import com. easyactivity.entity.AuditLog;
import com. easyactivity.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 管理员服务接口
 */
public interface AdminService {

    // 用户管理
    List<User> getAllUsers();
    List<User> getUsersByRole(Integer role);
    void updateUserRole(Long userId, Integer role);
    void batchDeleteUsers(List<Long> userIds);

    // 活动审核
    List<Activity> getAllActivitiesWithCreator();
    List<Activity> getActivitiesByAuditStatus(Integer auditStatus);
    void auditActivity(Long activityId, Integer auditStatus, Long auditorId, String auditComment);
    void batchDeleteActivities(List<Long> activityIds);
    void forceDeleteActivity(Long activityId);

    // 审核记录
    List<AuditLog> getAuditLogsByActivityId(Long activityId);
    List<AuditLog> getAllAuditLogs(Integer limit);

    // 基础统计
    Long countTotalUsers();
    Long countTotalActivities();
    Long countTotalParticipations();
    Long countPendingAudits();
    Long countTodayNewUsers();
    Long countTodayNewActivities();
    Long countWeekNewUsers();
    Long countMonthNewUsers();
    Long countOngoingActivities();
    Long countFinishedActivities();

    // 排行榜
    List<Activity> getPopularActivities(Integer limit);
    List<Activity> getRecentActivities(Integer limit);
    List<User> getActiveUsers(Integer limit);
    Double calculateAvgParticipantsPerActivity();
    Double calculateActivityFillRate();

    // 高级统计
    List<Map<String, Object>> getUserGrowthByDateRange(String startDate, String endDate);
    List<Map<String, Object>> getActivityGrowthByDateRange(String startDate, String endDate);
    List<Map<String, Object>> countActivitiesByStatus();
    List<Map<String, Object>> countParticipationsByStatus();
    List<Activity> getTopActivitiesByParticipants(Integer limit);
    List<Map<String, Object>> getTopCreators(Integer limit);
    List<Map<String, Object>> countActivitiesByLocation();
    List<Map<String, Object>> countActivitiesByMonth();

}