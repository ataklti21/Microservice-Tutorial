package com.atuka.notification;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages =
        {"com.atuka.notification",
                "com.atuka.Rabbitamqp"})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner
//            (RabbitMaMessageProducer producer, NotificationConfig config) {
//
//        return args -> {
//            producer.publish(new customerDetail(1,"Abreha",
//                            "Tesfay","abreha@gmail.com"), config.getInternalExchange(),
//                    config.getInternalNotificationRoutingKey());
//        };
//    }
//    record customerDetail(
//          Integer customerId,
//          String firstName,
//          String lastName,
//          String emailAddress
//    ){
//
//    }
}
