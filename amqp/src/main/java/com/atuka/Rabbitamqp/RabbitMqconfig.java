package com.atuka.Rabbitamqp;


import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
@EnableAutoConfiguration
public class RabbitMqconfig {
    //Injecting Connection factory
    private final ConnectionFactory connectionFactory;

    /**
     * These Methods allow to develop sender and receiver applications
     *The ampqTemplate allows as to send message to the queue
     * @return a rabbit Template
     */
    @Bean
    public AmqpTemplate amqpTemplate() {
        //Declaring and instantiation of RabbitTemplate to send and receive messages
        //and passing the connection factory as argument
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //this helps to send a message as a json
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    /**
     * this allows us to receive messages from the queue
     * @return simpleRabbitListenerContainerFactory Object
     */
    @Bean
    //This is A message Listener
    public SimpleRabbitListenerContainerFactory simpleRoutingConnectionFactory() {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter());
        return factory;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
