package com.atuka.notification;

import com.atuka.amqp.RabbitMaMessageProducer;
import com.atuka.notification.notificationConfig.NotificationConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(scanBasePackages =
        {"com.atuka.notification",
                "com.atuka.amqp"})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner
            (RabbitMaMessageProducer producer, NotificationConfig config) {

        return args -> {
            producer.publish("foo", config.getInternalExchange(),
                    config.getInternalNotificationRoutingKey());
        };
    }
}
