package com.atuka.notification.notificationConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@EnableAutoConfiguration
@AllArgsConstructor
@NoArgsConstructor
@Component
public class NotificationConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;
    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue;
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;


    //creating  Topic Exchange
    @Bean
    public TopicExchange internalExchange() {
        return new TopicExchange(this.internalExchange);
    }
//Creating queue
    @Bean
    public Queue notificationQueue() {
        return new Queue(this.notificationQueue);
    }
    //Binding queue and exchange using the routing key
    @Bean
    public Binding notificationToExchange(){
        return BindingBuilder.bind(notificationQueue())
                .to(internalExchange())
                .with(internalNotificationRoutingKey);
    }
}
