package com.easyactivity.service.impl;

import com.easyactivity.entity.Notification;
import com.easyactivity.mapper.NotificationMapper;
import com.easyactivity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Override
    @Transactional
    public Notification sendNotification(Long userId, String title, String content, String type, Long relatedId) {
        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setTitle(title);
        notification.setContent(content);
        notification.setType(type);
        notification. setRelatedId(relatedId);
        notification.setIsRead(0);
        notification.setCreateTime(LocalDateTime.now());

        notificationMapper.insert(notification);
        return notification;
    }

    @Override
    public Notification getById(Long id) {
        return notificationMapper. selectById(id);
    }

    @Override
    public List<Notification> getByUserId(Long userId) {
        return notificationMapper.selectByUserId(userId);
    }

    @Override
    public List<Notification> getUnreadByUserId(Long userId) {
        return notificationMapper. selectUnreadByUserId(userId);
    }

    @Override
    public Integer getUnreadCount(Long userId) {
        Integer count = notificationMapper.countUnreadByUserId(userId);
        return count != null ? count : 0;
    }

    @Override
    @Transactional
    public void markAsRead(Long id) {
        Notification notification = notificationMapper.selectById(id);
        if (notification == null) {
            throw new RuntimeException("通知不存在");
        }
        if (notification.getIsRead() == 1) {
            throw new RuntimeException("通知已读");
        }
        notificationMapper.markAsRead(id);
    }

    @Override
    @Transactional
    public void markAllAsRead(Long userId) {
        notificationMapper.markAllAsReadByUserId(userId);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Notification notification = notificationMapper.selectById(id);
        if (notification == null) {
            throw new RuntimeException("通知不存在");
        }
        notificationMapper.deleteById(id);
    }

    @Override
    public List<Notification> getByUserIdAndType(Long userId, String type) {
        return notificationMapper.selectByUserIdAndType(userId, type);
    }

    @Override
    @Transactional
    public void clearAll(Long userId) {
        notificationMapper.deleteByUserId(userId);
    }
}