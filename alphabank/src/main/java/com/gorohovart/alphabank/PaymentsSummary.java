package com.gorohovart.alphabank;

public class PaymentsSummary {
    private double min;
    private double max;
    private double sum;

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void countPayment(Payment payment) {
        sum += payment.getAmount();
        min = Math.min(min, payment.getAmount());
        max = Math.max(max, payment.getAmount());

    }
}
