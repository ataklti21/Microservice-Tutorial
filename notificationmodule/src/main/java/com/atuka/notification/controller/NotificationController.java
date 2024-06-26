package com.atuka.notification.controller;

import com.atuka.clients.faurd.Request.NotificationRequest;

import com.atuka.notification.Service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/notification")
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public void sendNotification(@RequestBody
                                     NotificationRequest notificationRequest){
        log.info("New notification Created{}",notificationRequest);
        notificationService.send(notificationRequest);
    }
}
