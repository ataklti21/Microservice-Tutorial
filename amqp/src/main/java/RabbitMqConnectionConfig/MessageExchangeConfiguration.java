package RabbitMqConnectionConfig;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.management.Query;

@Component
@Data
@EnableAutoConfiguration
@AllArgsConstructor
@NoArgsConstructor

public class MessageExchangeConfiguration {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;
    @Value("${rabbitmq.queues.notification}")
    private String notificationQueue;
    @Value("${rabbitmq.routing-keys.internal-notification}")
    private String internalNotificationRoutingKey;

    //Creating Exchange
    @Bean
    public Exchange exchange(){
        return  new TopicExchange(this.internalExchange,false,false);
    }
    //Creating Queue

    public Queue queue(){
        return new Queue(this.notificationQueue,false);
    }
    //Binding Exchange and the queue using routing key
    public Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(this.internalNotificationRoutingKey).noargs();
    }
}
