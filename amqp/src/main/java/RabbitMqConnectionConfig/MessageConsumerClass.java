package RabbitMqConnectionConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumerClass {
  private   MessageExchangeConfiguration configuration;
 private final String config = configuration.getNotificationQueue();
    @RabbitListener(queues ="${rabbitmq.routing-keys.internal-notification}")
    public void consumeMessage(String message){
        System.out.println("Message Received "+"-"+message);
    }
}
