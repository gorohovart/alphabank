package com.gorohovart.alphabank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentsProcessor {

    @Autowired
    private AnalyticsStorage storage;


    @KafkaListener(topics = "RAW_PAYMENTS")
    public void listen(Payment message) {
        System.out.println("Received Messasge in group foo: " + message);

        addStats(message);
    }

    private void addStats(Payment message) {
        UserStats userStats = storage.getUserStatsMap().getOrDefault(message.getUserId(), new UserStats(message.getUserId()));
        userStats.addPayment(message);
    }
}
