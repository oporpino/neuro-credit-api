package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class FixedCredit extends Credit {
    private static final double INTEREST_RATE = 5.0;
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 25;

    public FixedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    @Override
    public double calculateLoan(double amount, int months) {
        double monthlyRate = INTEREST_RATE / 12 / 100;
        double numerator = amount * monthlyRate * Math.pow(1 + monthlyRate, months);
        double denominator = Math.pow(1 + monthlyRate, months) - 1;
        return numerator / denominator;
    }
}