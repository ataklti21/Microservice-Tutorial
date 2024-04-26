package com.atuka.clients.faurd.Request;

import java.time.LocalDateTime;

public record NotificationRequest(Integer customerId,  String customerEmail, String message) {
}
