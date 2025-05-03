package br.com.neurotech.neurocreditapi.entity.credit;

import br.com.neurotech.neurocreditapi.config.Constants;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;

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