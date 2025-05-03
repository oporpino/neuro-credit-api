package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.config.Constants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class ConsignedCredit extends Credit {

    public ConsignedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        return client.getAge() >= Constants.Credit.Consigned.MIN_AGE;
    }
}