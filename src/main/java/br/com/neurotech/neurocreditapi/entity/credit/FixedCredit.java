package br.com.neurotech.neurocreditapi.entity.credit;

import br.com.neurotech.neurocreditapi.config.Constants;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;

public class FixedCredit extends Credit {

    public FixedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= Constants.Credit.Fixed.MIN_AGE && age <= Constants.Credit.Fixed.MAX_AGE;
    }
}