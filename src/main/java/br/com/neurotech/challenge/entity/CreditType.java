package br.com.neurotech.challenge.entity;

import br.com.neurotech.challenge.entity.credit.ConsignedCredit;
import br.com.neurotech.challenge.entity.credit.Credit;
import br.com.neurotech.challenge.entity.credit.FixedCredit;
import br.com.neurotech.challenge.entity.credit.VariableCredit;

public enum CreditType {
    CONSIGNED(ConsignedCredit.class),
    VARIABLE(VariableCredit.class),
    FIXED(FixedCredit.class);

    private final Class<? extends Credit> clazz;

    CreditType(Class<? extends Credit> clazz) {
        this.clazz = clazz;
    }

    public Credit getInstance(NeurotechClient client, VehicleModel vehicleModel) {
        try {
            return clazz.getDeclaredConstructor(NeurotechClient.class, VehicleModel.class)
                    .newInstance(client, vehicleModel);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create instance of " + clazz, e);
        }
    }
}