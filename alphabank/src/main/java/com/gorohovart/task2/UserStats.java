package com.gorohovart.task2;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserStats {
    private String userId;
    private BigDecimal totalSum = BigDecimal.valueOf(0);
    private HashMap<Integer, PaymentsSummary> analyticInfo = new HashMap<>();

    @JsonIgnore
    private Map<Integer, Integer> categoryToPaymentCount = new HashMap<>();

    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();

    public UserStats(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(BigDecimal totalSum) {
        this.totalSum = totalSum;
    }

    public HashMap<Integer, PaymentsSummary> getAnalyticInfo() {
        return analyticInfo;
    }

    public void setAnalyticInfo(HashMap<Integer, PaymentsSummary> analyticInfo) {
        this.analyticInfo = analyticInfo;
    }


    @JsonIgnore
    public void addPayment(Payment payment) {
        payments.add(payment);
        totalSum = totalSum.add(payment.getAmount());
        analyticInfo.putIfAbsent(payment.getCategoryId(), new PaymentsSummary());
        PaymentsSummary paymentsSummary = analyticInfo.get(payment.getCategoryId());
        paymentsSummary.countPayment(payment);

        categoryToPaymentCount.putIfAbsent(payment.getCategoryId(), 1);
        categoryToPaymentCount.put(payment.getCategoryId(), categoryToPaymentCount.get(payment.getCategoryId()) + 1);
    }

    @JsonIgnore
    public Stats getStats() {

        int maxCat = -1;
        int max = Integer.MIN_VALUE;

        int minCat = -1;
        int min = Integer.MAX_VALUE;

        for(Map.Entry<Integer, Integer> entry : categoryToPaymentCount.entrySet()) {
            if (entry.getValue() > max) {
                maxCat = entry.getKey();
                max = entry.getValue();
            }

            if (entry.getValue() < min) {
                minCat = entry.getKey();
                min = entry.getValue();
            }
        }

        int maxSumCat = -1;
        BigDecimal maxSum = BigDecimal.valueOf(Integer.MIN_VALUE);

        int minSumCat = -1;
        BigDecimal minSum = BigDecimal.valueOf(Integer.MAX_VALUE);

        for(Map.Entry<Integer, PaymentsSummary> entry : analyticInfo.entrySet()) {
            if (entry.getValue().getSum().compareTo(maxSum) > 0) {
                maxSumCat = entry.getKey();
                maxSum = entry.getValue().getSum();
            }

            if (entry.getValue().getSum().compareTo(minSum) <= 0) {
                minSumCat = entry.getKey();
                minSum = entry.getValue().getSum();
            }
        }



        return new Stats(maxCat, minCat, maxSumCat, minSumCat);
    }

    @JsonIgnore
    public Collection<Template> getTemplates() {
        return payments.stream().collect(Collectors.groupingBy(Payment::getAmount, Collectors.groupingBy(Payment::getRecipientId, Collectors.groupingBy(Payment::getCategoryId))))
                .entrySet().stream()
                .flatMap(entry -> {
                    BigDecimal amount = entry.getKey();
                    return entry.getValue().entrySet().stream().flatMap(entry1 -> {
                        String recipient = entry1.getKey();
                        return entry1.getValue().entrySet().stream().filter(entry2 -> entry2.getValue().size() >= 3)
                                .map(entry2 -> new Template(recipient, entry2.getKey(), amount));
                    });
                }).collect(Collectors.toList());
    }

    public class Template {
        private String recipientId;
        private Integer categoryId;
        private BigDecimal amount;

        public Template(String recipientId, Integer categoryId, BigDecimal amount) {
            this.recipientId = recipientId;
            this.categoryId = categoryId;
            this.amount = amount;
        }

        public String getRecipientId() {
            return recipientId;
        }

        public void setRecipientId(String recipientId) {
            this.recipientId = recipientId;
        }

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }
    }

    public class Stats {
        private Integer oftenCategoryId;
        private Integer rareCategoryId;
        private Integer maxAmountCategoryId;
        private Integer minAmountCategoryId;

        public Stats(Integer oftenCategoryId, Integer rareCategoryId, Integer maxAmountCategoryId, Integer minAmountCategoryId) {
            this.oftenCategoryId = oftenCategoryId;
            this.rareCategoryId = rareCategoryId;
            this.maxAmountCategoryId = maxAmountCategoryId;
            this.minAmountCategoryId = minAmountCategoryId;
        }

        public Integer getOftenCategoryId() {
            return oftenCategoryId;
        }

        public void setOftenCategoryId(Integer oftenCategoryId) {
            this.oftenCategoryId = oftenCategoryId;
        }

        public Integer getRareCategoryId() {
            return rareCategoryId;
        }

        public void setRareCategoryId(Integer rareCategoryId) {
            this.rareCategoryId = rareCategoryId;
        }

        public Integer getMaxAmountCategoryId() {
            return maxAmountCategoryId;
        }

        public void setMaxAmountCategoryId(Integer maxAmountCategoryId) {
            this.maxAmountCategoryId = maxAmountCategoryId;
        }

        public Integer getMinAmountCategoryId() {
            return minAmountCategoryId;
        }

        public void setMinAmountCategoryId(Integer minAmountCategoryId) {
            this.minAmountCategoryId = minAmountCategoryId;
        }


    }
}
