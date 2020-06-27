package com.gorohovart.task2;

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

    private void addStats(Payment payment) {
        if (!payment.isValid()) {
            return;
        }
        storage.getUserStatsMap().putIfAbsent(payment.getUserId(), new UserStats(payment.getUserId()));
        UserStats userStats = storage.getUserStatsMap().get(payment.getUserId());
        userStats.addPayment(payment);
    }
}
