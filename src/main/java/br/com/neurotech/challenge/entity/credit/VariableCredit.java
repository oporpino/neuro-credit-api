package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class VariableCredit extends Credit {
    private static final int MIN_AGE = 21;
    private static final int MAX_AGE = 65;

    public VariableCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= MIN_AGE && age <= MAX_AGE;
    }
}