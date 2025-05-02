package br.com.neurotech.challenge.entity;

public enum CreditType {
    FIXED_INTEREST(5.0, 18, 25, null, null),
    VARIABLE_INTEREST(null, 21, 65, 5000.0, 15000.0),
    CONSIGNED(null, 65, null, null, null);

    private final Double interestRate;
    private final Integer minAge;
    private final Integer maxAge;
    private final Double minIncome;
    private final Double maxIncome;

    CreditType(Double interestRate, Integer minAge, Integer maxAge, Double minIncome, Double maxIncome) {
        this.interestRate = interestRate;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.minIncome = minIncome;
        this.maxIncome = maxIncome;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public Double getMinIncome() {
        return minIncome;
    }

    public Double getMaxIncome() {
        return maxIncome;
    }
} 