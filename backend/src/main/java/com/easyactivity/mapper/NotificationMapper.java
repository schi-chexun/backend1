package com.easyactivity.mapper;

import com.easyactivity.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NotificationMapper {

    /**
     * 插入通知
     */
    int insert(Notification notification);

    /**
     * 根据ID查询
     */
    Notification selectById(@Param("id") Long id);

    /**
     * 根据用户ID查询所有通知
     */
    List<Notification> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询未读通知
     */
    List<Notification> selectUnreadByUserId(@Param("userId") Long userId);

    /**
     * 统计用户未读通知数量
     */
    Integer countUnreadByUserId(@Param("userId") Long userId);

    /**
     * 更新通知
     */
    int update(Notification notification);

    /**
     * 标记单个通知为已读
     */
    int markAsRead(@Param("id") Long id);

    /**
     * 标记用户所有通知为已读
     */
    int markAllAsReadByUserId(@Param("userId") Long userId);

    /**
     * 删除通知
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据类型查询通知
     */
    List<Notification> selectByUserIdAndType(@Param("userId") Long userId, @Param("type") String type);

    /**
     * 批量删除用户所有通知
     */
    int deleteByUserId(@Param("userId") Long userId);
}