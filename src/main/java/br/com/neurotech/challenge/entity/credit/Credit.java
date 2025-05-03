package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.config.Constants;
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
        return income >= Constants.Income.MIN && income <= Constants.Income.MAX;
    }

    protected boolean isEligibleForVehicle() {
        int age = client.getAge();
        double income = client.getIncome();

        if (VehicleModel.HATCH.equals(vehicleModel)) {
            return age >= Constants.Vehicle.HATCH_MIN_AGE && age <= Constants.Vehicle.HATCH_MAX_AGE;
        } else if (VehicleModel.SUV.equals(vehicleModel)) {
            return age >= Constants.Vehicle.SUV_MIN_AGE && income > Constants.Income.SUV_MIN;
        }
        return false;
    }

    public boolean isEligible() {
        return isIncomeValid() && isEligibleForVehicle() && isTypeEligible();
    }

    protected abstract boolean isTypeEligible();
}