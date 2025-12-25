package com.easyactivity.service;

import com.easyactivity.entity.Notification;
import java.util.List;

public interface NotificationService {

    /**
     * 发送通知
     */
    Notification sendNotification(Long userId, String title, String content, String type, Long relatedId);

    /**
     * 根据ID查询通知
     */
    Notification getById(Long id);

    /**
     * 获取用户所有通知
     */
    List<Notification> getByUserId(Long userId);

    /**
     * 获取用户未读通知
     */
    List<Notification> getUnreadByUserId(Long userId);

    /**
     * 获取用户未读通知数量
     */
    Integer getUnreadCount(Long userId);

    /**
     * 标记单个通知为已读
     */
    void markAsRead(Long id);

    /**
     * 标记所有通知为已读
     */
    void markAllAsRead(Long userId);

    /**
     * 删除通知
     */
    void delete(Long id);

    /**
     * 根据类型获取通知
     */
    List<Notification> getByUserIdAndType(Long userId, String type);

    /**
     * 清空用户所有通知
     */
    void clearAll(Long userId);
}