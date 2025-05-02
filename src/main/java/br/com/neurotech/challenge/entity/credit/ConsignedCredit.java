package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.constants.CreditConstants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class ConsignedCredit extends Credit {

    public ConsignedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        return client.getAge() >= CreditConstants.CONSIGNED_CREDIT_MIN_AGE;
    }
}