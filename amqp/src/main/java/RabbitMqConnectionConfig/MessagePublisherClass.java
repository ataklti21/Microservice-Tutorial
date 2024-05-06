package RabbitMqConnectionConfig;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisherClass {
    private RabbitTemplate rabbitTemplate;
private  MessageExchangeConfiguration config;
    public void MessageProducer(String message){
        rabbitTemplate.convertAndSend(config.getInternalExchange(),config.getInternalNotificationRoutingKey(),
                "This is a second option for sending Message to queue");
    }
}
