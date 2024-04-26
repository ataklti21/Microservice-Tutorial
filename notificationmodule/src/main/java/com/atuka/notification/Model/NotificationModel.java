package com.atuka.notification.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NotificationModel {

    @Id
    @SequenceGenerator(name = "notification_id_seq",
    sequenceName = "notification_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "notification_id_seq")
    private Integer notificationId;
    private Integer customerId;
    private String sender;
    private String customerEmail;
    private String message;
    private LocalDateTime sendAt;


}
