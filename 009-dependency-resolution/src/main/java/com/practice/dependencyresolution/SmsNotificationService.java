package com.practice.dependencyresolution;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificationService implements NotificationService {
    @Override
    public String sendNotification() {
        return "SMS notification sent";
    }
}
