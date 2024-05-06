package com.atuka.notification.rabbitMq;

import com.atuka.clients.faurd.Request.NotificationRequest;
import com.atuka.notification.Service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j

public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consume(NotificationRequest notificationRequest){
        log.info("Consuming from {}",notificationRequest);
        notificationService.send(notificationRequest);

    }
}
