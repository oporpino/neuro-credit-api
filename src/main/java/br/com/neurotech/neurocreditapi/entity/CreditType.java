package br.com.neurotech.neurocreditapi.entity;

import br.com.neurotech.neurocreditapi.entity.credit.ConsignedCredit;
import br.com.neurotech.neurocreditapi.entity.credit.Credit;
import br.com.neurotech.neurocreditapi.entity.credit.FixedCredit;
import br.com.neurotech.neurocreditapi.entity.credit.VariableCredit;

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