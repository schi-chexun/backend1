package com.easyactivity.service.impl;

import com.easyactivity.entity.Activity;
import com.easyactivity.entity.AuditLog;
import com.easyactivity.entity.User;
import com.easyactivity.mapper.AdminMapper;
import com.easyactivity.service.AdminService;
import org.springframework.beans. factory.annotation.Autowired;
import org.springframework.stereotype. Service;
import org.springframework. transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java. util.List;
import java. util.Map;

/**
 * 管理员服务实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    // 用户管理
    @Override
    public List<User> getAllUsers() {
        return adminMapper.selectAllUsers();
    }

    @Override
    public List<User> getUsersByRole(Integer role) {
        return adminMapper.selectUsersByRole(role);
    }

    @Override
    @Transactional
    public void updateUserRole(Long userId, Integer role) {
        int rows = adminMapper.updateUserRole(userId, role);
        if (rows == 0) {
            throw new RuntimeException("更新用户角色失败");
        }
    }

    @Override
    @Transactional
    public void batchDeleteUsers(List<Long> userIds) {
        if (userIds == null || userIds.isEmpty()) {
            throw new RuntimeException("用户ID列表不能为空");
        }
        adminMapper.deleteUsersByIds(userIds);
    }

    // 活动审核
    @Override
    public List<Activity> getAllActivitiesWithCreator() {
        return adminMapper.selectAllActivitiesWithCreator();
    }

    @Override
    public List<Activity> getActivitiesByAuditStatus(Integer auditStatus) {
        return adminMapper.selectActivitiesByAuditStatus(auditStatus);
    }

    @Override
    @Transactional
    public void auditActivity(Long activityId, Integer auditStatus, Long auditorId, String auditComment) {
        adminMapper.updateActivityAuditStatus(activityId, auditStatus);

        AuditLog auditLog = new AuditLog();
        auditLog.setActivityId(activityId);
        auditLog.setAuditorId(auditorId);
        auditLog.setAuditStatus(auditStatus);
        auditLog.setAuditComment(auditComment);
        auditLog.setAuditTime(LocalDateTime.now());
        adminMapper.insertAuditLog(auditLog);
    }

    @Override
    @Transactional
    public void batchDeleteActivities(List<Long> activityIds) {
        if (activityIds == null || activityIds.isEmpty()) {
            throw new RuntimeException("活动ID列表不能为空");
        }
        adminMapper.deleteActivitiesByIds(activityIds);
    }

    @Override
    @Transactional
    public void forceDeleteActivity(Long activityId) {
        adminMapper.forceDeleteActivity(activityId);
    }

    // 审核记录
    @Override
    public List<AuditLog> getAuditLogsByActivityId(Long activityId) {
        return adminMapper. selectAuditLogsWithDetails(activityId);
    }

    @Override
    public List<AuditLog> getAllAuditLogs(Integer limit) {
        return adminMapper.selectAllAuditLogs(limit);
    }

    // 基础统计
    @Override
    public Long countTotalUsers() {
        return adminMapper.countTotalUsers();
    }

    @Override
    public Long countTotalActivities() {
        return adminMapper. countTotalActivities();
    }

    @Override
    public Long countTotalParticipations() {
        return adminMapper.countTotalParticipations();
    }

    @Override
    public Long countPendingAudits() {
        return adminMapper.countPendingAudits();
    }

    @Override
    public Long countTodayNewUsers() {
        return adminMapper.countTodayNewUsers();
    }

    @Override
    public Long countTodayNewActivities() {
        return adminMapper.countTodayNewActivities();
    }

    @Override
    public Long countWeekNewUsers() {
        return adminMapper.countWeekNewUsers();
    }

    @Override
    public Long countMonthNewUsers() {
        return adminMapper.countMonthNewUsers();
    }

    @Override
    public Long countOngoingActivities() {
        return adminMapper.countOngoingActivities();
    }

    @Override
    public Long countFinishedActivities() {
        return adminMapper.countFinishedActivities();
    }

    // 排行榜
    @Override
    public List<Activity> getPopularActivities(Integer limit) {
        return adminMapper. selectPopularActivities(limit);
    }

    @Override
    public List<Activity> getRecentActivities(Integer limit) {
        return adminMapper. selectRecentActivities(limit);
    }

    @Override
    public List<User> getActiveUsers(Integer limit) {
        return adminMapper.selectActiveUsers(limit);
    }

    @Override
    public Double calculateAvgParticipantsPerActivity() {
        return adminMapper.calculateAvgParticipantsPerActivity();
    }

    @Override
    public Double calculateActivityFillRate() {
        return adminMapper. calculateActivityFillRate();
    }

    // 高级统计
    @Override
    public List<Map<String, Object>> getUserGrowthByDateRange(String startDate, String endDate) {
        return adminMapper.selectUserGrowthByDateRange(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> getActivityGrowthByDateRange(String startDate, String endDate) {
        return adminMapper.selectActivityGrowthByDateRange(startDate, endDate);
    }

    @Override
    public List<Map<String, Object>> countActivitiesByStatus() {
        return adminMapper.countActivitiesByStatus();
    }

    @Override
    public List<Map<String, Object>> countParticipationsByStatus() {
        return adminMapper.countParticipationsByStatus();
    }

    @Override
    public List<Activity> getTopActivitiesByParticipants(Integer limit) {
        return adminMapper.selectTopActivitiesByParticipants(limit);
    }

    @Override
    public List<Map<String, Object>> getTopCreators(Integer limit) {
        return adminMapper.selectTopCreators(limit);
    }

    @Override
    public List<Map<String, Object>> countActivitiesByLocation() {
        return adminMapper.countActivitiesByLocation();
    }

    @Override
    public List<Map<String, Object>> countActivitiesByMonth() {
        return adminMapper. countActivitiesByMonth();
    }
}