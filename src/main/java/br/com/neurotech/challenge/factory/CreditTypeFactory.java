package br.com.neurotech.challenge.factory;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.entity.credit.Credit;

public class CreditTypeFactory {

    public static Credit getApplicableCredit(NeurotechClient client, VehicleModel vehicleModel) {
        // First check for Consigned Credit (highest priority for seniors)
        Credit consignedCredit = CreditType.CONSIGNED.getInstance(client, vehicleModel);
        if (consignedCredit.isEligible()) {
            return consignedCredit;
        }

        // Then check for Variable Credit (middle range)
        Credit variableCredit = CreditType.VARIABLE.getInstance(client, vehicleModel);
        if (variableCredit.isEligible()) {
            return variableCredit;
        }

        // Finally check for Fixed Credit (youngest clients)
        Credit fixedCredit = CreditType.FIXED.getInstance(client, vehicleModel);
        if (fixedCredit.isEligible()) {
            return fixedCredit;
        }

        return null; // Client is not eligible for any credit type
    }
}