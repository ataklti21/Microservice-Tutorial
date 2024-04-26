package com.atuka.notification.Repositor;

import com.atuka.notification.Model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationModel,Integer> {
}
