package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class ConsignedCredit extends Credit {
    private static final int MIN_AGE = 65;
    private static final double ANNUAL_RATE = 3.0;

    public ConsignedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        return client.getAge() >= MIN_AGE;
    }

    @Override
    public double calculateLoan(double amount, int months) {
        double monthlyRate = ANNUAL_RATE / 12 / 100;
        double numerator = amount * monthlyRate * Math.pow(1 + monthlyRate, months);
        double denominator = Math.pow(1 + monthlyRate, months) - 1;
        return numerator / denominator;
    }
}