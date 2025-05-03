package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.config.Constants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

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