package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public abstract class Credit {
    protected final NeurotechClient client;
    protected final VehicleModel vehicleModel;
    protected static final double MIN_INCOME = 5000.0;
    protected static final double MAX_INCOME = 15000.0;
    protected static final int HATCH_MIN_AGE = 23;
    protected static final int HATCH_MAX_AGE = 49;
    protected static final int SUV_MIN_AGE = 21;
    protected static final int SUV_MAX_AGE = 65;
    protected static final double SUV_MIN_INCOME = 8000.0;

    protected Credit(NeurotechClient client, VehicleModel vehicleModel) {
        this.client = client;
        this.vehicleModel = vehicleModel;
    }

    protected boolean isIncomeValid() {
        double income = client.getIncome();
        return income >= MIN_INCOME && income <= MAX_INCOME;
    }

    protected boolean isEligibleForVehicle() {
        int age = client.getAge();
        double income = client.getIncome();

        if (VehicleModel.HATCH.equals(vehicleModel)) {
            return age >= HATCH_MIN_AGE && age <= HATCH_MAX_AGE;
        } else if (VehicleModel.SUV.equals(vehicleModel)) {
            return age >= SUV_MIN_AGE && age <= SUV_MAX_AGE && income >= SUV_MIN_INCOME;
        }
        return false;
    }

    public boolean isEligible() {
        return isIncomeValid() && isEligibleForVehicle() && isTypeEligible();
    }

    protected abstract boolean isTypeEligible();

    public abstract double calculateLoan(double amount, int months);
}