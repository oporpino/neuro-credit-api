package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class VariableCredit extends Credit {
    private static final int MIN_AGE = 21;
    private static final int MAX_AGE = 65;
    private static final double BASE_RATE = 8.0;

    public VariableCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    @Override
    public double calculateLoan(double amount, int months) {
        double riskFactor = amount > 50000 ? 2.0 : 1.0; // Higher risk for larger loans
        double effectiveRate = BASE_RATE * riskFactor;

        double monthlyRate = effectiveRate / 12 / 100;
        double numerator = amount * monthlyRate * Math.pow(1 + monthlyRate, months);
        double denominator = Math.pow(1 + monthlyRate, months) - 1;
        return numerator / denominator;
    }
}