package com.practice.dependencyresolution;

import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {
    @Override
    public String sendNotification() {
            return "Email notification sent";
    }

}
