package com.gorohovart.task2;

import java.math.BigDecimal;

public class Payment {
    private String ref;
    private Integer categoryId;
    private String userId;
    private String recipientId;
    private String desc;
    private BigDecimal amount;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isValid() {
        return !(categoryId == null || userId == null ||  recipientId == null || desc == null ||amount == null);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "categoryId=" + categoryId +
                ", userId='" + userId + '\'' +
                ", recipientId='" + recipientId + '\'' +
                ", desc='" + desc + '\'' +
                ", amount=" + amount +
                '}';
    }
}
