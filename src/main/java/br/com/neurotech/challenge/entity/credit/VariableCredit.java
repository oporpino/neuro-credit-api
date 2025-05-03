package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.config.Constants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class VariableCredit extends Credit {

    public VariableCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= Constants.Credit.Variable.MIN_AGE && age <= Constants.Credit.Variable.MAX_AGE;
    }
}