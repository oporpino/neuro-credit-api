package br.com.neurotech.neurocreditapi.entity.credit;

import br.com.neurotech.neurocreditapi.config.Constants;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;

public class ConsignedCredit extends Credit {

    public ConsignedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        return client.getAge() >= Constants.Credit.Consigned.MIN_AGE;
    }
}