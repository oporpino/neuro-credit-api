package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.constants.CreditConstants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public abstract class Credit {
    protected final NeurotechClient client;
    protected final VehicleModel vehicleModel;

    protected Credit(NeurotechClient client, VehicleModel vehicleModel) {
        this.client = client;
        this.vehicleModel = vehicleModel;
    }

    protected boolean isIncomeValid() {
        double income = client.getIncome();
        return income >= CreditConstants.MIN_INCOME && income <= CreditConstants.MAX_INCOME;
    }

    protected boolean isEligibleForVehicle() {
        int age = client.getAge();
        double income = client.getIncome();

        if (VehicleModel.HATCH.equals(vehicleModel)) {
            return age >= CreditConstants.HATCH_MIN_AGE && age <= CreditConstants.HATCH_MAX_AGE;
        } else if (VehicleModel.SUV.equals(vehicleModel)) {
            return age >= CreditConstants.SUV_MIN_AGE && income > CreditConstants.SUV_MIN_INCOME;
        }
        return false;
    }

    public boolean isEligible() {
        return isIncomeValid() && isEligibleForVehicle() && isTypeEligible();
    }

    protected abstract boolean isTypeEligible();
}