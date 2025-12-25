package com.easyactivity.utils;

import com.easyactivity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息通知工具类
 */
@Component
public class NotificationUtil {

    @Autowired
    private NotificationService notificationService;

    /**
     * 发送活动通知
     */
    public void sendActivityNotification(Long userId, String activityName, Long activityId) {
        String title = "活动通知";
        String content = String.format("您关注的活动【%s】有新动态", activityName);
        notificationService.sendNotification(userId, title, content, "ACTIVITY", activityId);
    }

    /**
     * 发送评论通知
     */
    public void sendCommentNotification(Long userId, String commenterName, Long activityId) {
        String title = "评论通知";
        String content = String.format("%s 评论了您的活动", commenterName);
        notificationService.sendNotification(userId, title, content, "COMMENT", activityId);
    }

    /**
     * 发送报名通知
     */
    public void sendRegisterNotification(Long userId, String activityName, Long activityId) {
        String title = "报名成功";
        String content = String.format("您已成功报名活动【%s】", activityName);
        notificationService. sendNotification(userId, title, content, "REGISTER", activityId);
    }

    /**
     * 发送系统通知
     */
    public void sendSystemNotification(Long userId, String title, String content) {
        notificationService.sendNotification(userId, title, content, "SYSTEM", null);
    }
}