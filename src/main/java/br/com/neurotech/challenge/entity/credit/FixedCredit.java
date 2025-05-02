package br.com.neurotech.challenge.entity.credit;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

public class FixedCredit extends Credit {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 25;

    public FixedCredit(NeurotechClient client, VehicleModel vehicleModel) {
        super(client, vehicleModel);
    }

    @Override
    protected boolean isTypeEligible() {
        int age = client.getAge();
        return age >= MIN_AGE && age <= MAX_AGE;
    }
}