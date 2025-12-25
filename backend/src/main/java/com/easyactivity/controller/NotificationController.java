package com.easyactivity.controller;

import com.easyactivity.common.Result;
import com.easyactivity.entity.Notification;
import com.easyactivity.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java. util.List;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 测试接口
     */
    @GetMapping("/test")
    public String test() {
        return "NotificationController works!";
    }

    /**
     * 发送通知
     */
    @PostMapping("/send")
    public Result<Notification> send(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String type,
            @RequestParam(required = false) Long relatedId) {

        Notification notification = notificationService.sendNotification(
                userId, title, content, type, relatedId
        );
        return Result.success(notification);
    }

    /**
     * 根据ID查询通知
     */
    @GetMapping("/{id}")
    public Result<Notification> getById(@PathVariable Long id) {
        Notification notification = notificationService.getById(id);
        if (notification == null) {
            return Result.error("通知不存在");
        }
        return Result.success(notification);
    }

    /**
     * 获取用户所有通知
     */
    @GetMapping("/list")
    public Result<List<Notification>> getList(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getByUserId(userId);
        return Result. success(notifications);
    }

    /**
     * 获取用户未读通知
     */
    @GetMapping("/unread")
    public Result<List<Notification>> getUnread(@RequestParam Long userId) {
        List<Notification> notifications = notificationService.getUnreadByUserId(userId);
        return Result.success(notifications);
    }

    /**
     * 获取未读通知数量
     */
    @GetMapping("/unread-count")
    public Result<Integer> getUnreadCount(@RequestParam Long userId) {
        Integer count = notificationService.getUnreadCount(userId);
        return Result.success(count);
    }

    /**
     * 标记单个通知为已读
     */
    @PutMapping("/read/{id}")
    public Result<String> markAsRead(@PathVariable Long id) {
        try {
            notificationService.markAsRead(id);
            return Result.success("标记成功");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 标记所有通知为已读
     */
    @PutMapping("/read-all")
    public Result<String> markAllAsRead(@RequestParam Long userId) {
        notificationService.markAllAsRead(userId);
        return Result.success("全部标记成功");
    }

    /**
     * 删除通知
     */
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        try {
            notificationService.delete(id);
            return Result. success("删除成功");
        } catch (RuntimeException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 根据类型获取通知
     */
    @GetMapping("/type")
    public Result<List<Notification>> getByType(
            @RequestParam Long userId,
            @RequestParam String type) {
        List<Notification> notifications = notificationService. getByUserIdAndType(userId, type);
        return Result. success(notifications);
    }

    /**
     * 清空所有通知
     */
    @DeleteMapping("/clear")
    public Result<String> clearAll(@RequestParam Long userId) {
        notificationService.clearAll(userId);
        return Result.success("清空成功");
    }
}