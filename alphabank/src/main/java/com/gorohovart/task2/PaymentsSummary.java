package com.gorohovart.task2;

import java.math.BigDecimal;

public class PaymentsSummary {
    private BigDecimal min = BigDecimal.valueOf(Double.MAX_VALUE);
    private BigDecimal max = BigDecimal.valueOf(0);
    private BigDecimal sum = BigDecimal.valueOf(0);

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public void countPayment(Payment payment) {
        sum = sum.add(payment.getAmount());
        min = min.min(payment.getAmount());
        max = max.max(payment.getAmount());

    }
}
