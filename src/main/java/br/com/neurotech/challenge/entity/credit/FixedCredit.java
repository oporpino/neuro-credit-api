package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.constants.CreditConstants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class FixedCredit extends Credit {

    public FixedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= CreditConstants.FIXED_CREDIT_MIN_AGE && age <= CreditConstants.FIXED_CREDIT_MAX_AGE;
    }
}