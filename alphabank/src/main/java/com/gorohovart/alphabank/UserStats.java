package com.gorohovart.alphabank;

import java.util.HashMap;

public class UserStats {
    private String userId;
    private double totalSum;
    private HashMap<Integer, PaymentsSummary> analyticInfo = new HashMap<>();

    public UserStats(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public HashMap<Integer, PaymentsSummary> getAnalyticInfo() {
        return analyticInfo;
    }

    public void setAnalyticInfo(HashMap<Integer, PaymentsSummary> analyticInfo) {
        this.analyticInfo = analyticInfo;
    }

    public void addPayment(Payment payment) {
        if (!payment.isValid()) {
            return;
        }

        totalSum += payment.getAmount();
        PaymentsSummary paymentsSummary = analyticInfo.getOrDefault(payment.getCategoryId(), new PaymentsSummary());
        paymentsSummary.countPayment(payment);
    }
}
