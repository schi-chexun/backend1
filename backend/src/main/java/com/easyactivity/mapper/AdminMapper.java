package com.easyactivity. mapper;

import com.easyactivity.entity.Activity;
import com.easyactivity. entity.AuditLog;
import com.easyactivity. entity.User;
import org. apache.ibatis.annotations. Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 管理员Mapper接口
 * 用于管理员模块的数据访问操作
 *
 * @author EasyActivity Team
 * @since 1.0
 */
@Mapper
public interface AdminMapper {

    // ====================================================================
    // 用户管理模块
    // ====================================================================

    /**
     * 查询所有用户
     * 按创建时间倒序排列
     *
     * @return 用户列表
     */
    List<User> selectAllUsers();

    /**
     * 根据角色查询用户
     *
     * @param role 角色：0-普通用户，1-管理员
     * @return 用户列表
     */
    List<User> selectUsersByRole(@Param("role") Integer role);

    /**
     * 更新用户角色
     *
     * @param id 用户ID
     * @param role 新角色
     * @return 影响的行数
     */
    int updateUserRole(@Param("id") Long id, @Param("role") Integer role);

    /**
     * 批量删除用户
     *
     * @param ids 用户ID列表
     * @return 影响的行数
     */
    int deleteUsersByIds(@Param("ids") List<Long> ids);


    // ====================================================================
    // 活动管理模块
    // ====================================================================

    /**
     * 查询所有活动（包含创建者信息）
     * 按创建时间倒序排列
     *
     * @return 活动列表
     */
    List<Activity> selectAllActivitiesWithCreator();

    /**
     * 根据审核状态查询活动
     *
     * @param auditStatus 审核状态：0-待审核，1-已通过，2-已拒绝
     * @return 活动列表
     */
    List<Activity> selectActivitiesByAuditStatus(@Param("auditStatus") Integer auditStatus);

    /**
     * 更新活动审核状态
     *
     * @param id 活动ID
     * @param auditStatus 审核状态
     * @return 影响的行数
     */
    int updateActivityAuditStatus(@Param("id") Long id, @Param("auditStatus") Integer auditStatus);

    /**
     * 批量删除活动
     *
     * @param ids 活动ID列表
     * @return 影响的行数
     */
    int deleteActivitiesByIds(@Param("ids") List<Long> ids);

    /**
     * 强制删除活动（同时删除相关报名记录）
     *
     * @param activityId 活动ID
     * @return 影响的行数
     */
    int forceDeleteActivity(@Param("activityId") Long activityId);


    // ====================================================================
    // 审核记录模块
    // ====================================================================

    /**
     * 插入审核记录
     *
     * @param auditLog 审核记录对象
     * @return 影响的行数
     */
    int insertAuditLog(AuditLog auditLog);

    /**
     * 查询活动的审核历史
     *
     * @param activityId 活动ID
     * @return 审核记录列表
     */
    List<AuditLog> selectAuditLogsByActivityId(@Param("activityId") Long activityId);

    /**
     * 查询审核记录（包含审核人姓名和活动标题）
     *
     * @param activityId 活动ID
     * @return 审核记录列表（带详细信息）
     */
    List<AuditLog> selectAuditLogsWithDetails(@Param("activityId") Long activityId);

    /**
     * 查询所有审核记录
     *
     * @param limit 限制条数
     * @return 审核记录列表
     */
    List<AuditLog> selectAllAuditLogs(@Param("limit") Integer limit);


    // ====================================================================
    // 基础统计模块
    // ====================================================================

    /**
     * 统计总用户数
     *
     * @return 用户总数
     */
    Long countTotalUsers();

    /**
     * 统计总活动数
     *
     * @return 活动总数
     */
    Long countTotalActivities();

    /**
     * 统计总报名数
     *
     * @return 报名总数
     */
    Long countTotalParticipations();

    /**
     * 统计待审核活动数
     *
     * @return 待审核活动数
     */
    Long countPendingAudits();

    /**
     * 统计今日新增用户数
     *
     * @return 今日新增用户数
     */
    Long countTodayNewUsers();

    /**
     * 统计今日新增活动数
     *
     * @return 今日新增活动数
     */
    Long countTodayNewActivities();

    /**
     * 统计本周新增用户数
     *
     * @return 本周新增用户数
     */
    Long countWeekNewUsers();

    /**
     * 统计本月新增用户数
     *
     * @return 本月新增用户数
     */
    Long countMonthNewUsers();

    /**
     * 统计进行中的活动数
     *
     * @return 进行中的活动数
     */
    Long countOngoingActivities();

    /**
     * 统计已结束的活动数
     *
     * @return 已结束的活动数
     */
    Long countFinishedActivities();


    // ====================================================================
    // 排行榜/热门数据模块
    // ====================================================================

    /**
     * 查询热门活动（按报名人数排序）
     *
     * @param limit 限制条数
     * @return 活动列表
     */
    List<Activity> selectPopularActivities(@Param("limit") Integer limit);

    /**
     * 查询最近创建的活动
     *
     * @param limit 限制条数
     * @return 活动列表
     */
    List<Activity> selectRecentActivities(@Param("limit") Integer limit);

    /**
     * 查询最活跃的用户（按参与活动数排序）
     *
     * @param limit 限制条数
     * @return 用户列表
     */
    List<User> selectActiveUsers(@Param("limit") Integer limit);

    /**
     * 计算平均每个活动的报名人数
     *
     * @return 平均报名人数
     */
    Double calculateAvgParticipantsPerActivity();

    /**
     * 计算活动报名率
     *
     * @return 报名率（百分比）
     */
    Double calculateActivityFillRate();


    // ====================================================================
    // 高级统计/分析模块
    // ====================================================================

    /**
     * 按时间范围统计用户增长
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 日期-数量映射列表
     */
    List<Map<String, Object>> selectUserGrowthByDateRange(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    /**
     * 按时间范围统计活动增长
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 日期-数量映射列表
     */
    List<Map<String, Object>> selectActivityGrowthByDateRange(
            @Param("startDate") String startDate,
            @Param("endDate") String endDate
    );

    /**
     * 统计各状态的活动数量
     *
     * @return 状态-数量映射列表
     */
    List<Map<String, Object>> countActivitiesByStatus();

    /**
     * 统计各状态的报名数量
     *
     * @return 状态-数量映射列表
     */
    List<Map<String, Object>> countParticipationsByStatus();

    /**
     * 查询报名人数最多的前N个活动
     *
     * @param limit 限制条数
     * @return 活动列表（包含报名率）
     */
    List<Activity> selectTopActivitiesByParticipants(@Param("limit") Integer limit);

    /**
     * 查询创建活动最多的用户
     *
     * @param limit 限制条数
     * @return 用户及其创建活动数列表
     */
    List<Map<String, Object>> selectTopCreators(@Param("limit") Integer limit);

    /**
     * 按活动地点统计活动数量
     *
     * @return 地点-数量映射列表
     */
    List<Map<String, Object>> countActivitiesByLocation();

    /**
     * 按月统计活动数量（最近12个月）
     *
     * @return 月份-数量映射列表
     */
    List<Map<String, Object>> countActivitiesByMonth();
}