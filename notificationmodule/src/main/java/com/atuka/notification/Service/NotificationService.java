package com.atuka.notification.Service;

import com.atuka.clients.faurd.Request.NotificationRequest;
import com.atuka.notification.Model.NotificationModel;
import com.atuka.notification.Repositor.NotificationRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    public void send(NotificationRequest notificationRequest) {
        repository.saveAndFlush(NotificationModel.builder()
                .customerEmail(notificationRequest
                        .customerEmail())
                .sender("sender@gmail.com")
                .message(notificationRequest.message())
                .sendAt(LocalDateTime.now()).build());

    }
}
