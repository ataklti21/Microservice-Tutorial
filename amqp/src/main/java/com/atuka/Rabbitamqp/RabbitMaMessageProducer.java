package com.atuka.Rabbitamqp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMaMessageProducer {
    //injecting the AmqpTemplate
    public final AmqpTemplate amqpTemplate;

    public void publish(Object payLoad, String exchange, String routingKey) {
        log.info("Publishing to {} using routing key {}. " +
                "payload {}",exchange,routingKey,payLoad);
        amqpTemplate.convertAndSend(exchange, routingKey, payLoad);
        log.info("Published to {} using routing key {}. " +
                "payload {}",exchange,routingKey,payLoad);
    }
}
