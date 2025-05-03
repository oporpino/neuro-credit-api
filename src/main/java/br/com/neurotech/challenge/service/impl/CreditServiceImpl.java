package br.com.neurotech.challenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.constants.CreditConstants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;

@Service
public class CreditServiceImpl implements CreditService {

    @Autowired
    private ClientService clientService;

    @Override
    public boolean checkCredit(String clientId, VehicleModel vehicleModel) {
        NeurotechClient client = clientService.get(clientId);
        if (client == null) {
            return false;
        }

        return isEligibleForVehicle(client, vehicleModel);
    }

    private boolean isEligibleForVehicle(NeurotechClient client, VehicleModel vehicleModel) {
        int age = client.getAge();
        double income = client.getIncome();

        if (vehicleModel == VehicleModel.HATCH) {
            return age >= CreditConstants.HATCH_MIN_AGE &&
                    age <= CreditConstants.HATCH_MAX_AGE &&
                    income >= CreditConstants.MIN_INCOME &&
                    income <= CreditConstants.MAX_INCOME;
        } else if (vehicleModel == VehicleModel.SUV) {
            return age >= CreditConstants.SUV_MIN_AGE &&
                    age <= CreditConstants.VARIABLE_CREDIT_MAX_AGE &&
                    income >= CreditConstants.SUV_MIN_INCOME;
        }

        return false;
    }

    @Override
    public List<NeurotechClient> findEligibleClientsForHatch() {
        return clientService.getAll().stream()
                .filter(client -> isEligibleForVehicle(client, VehicleModel.HATCH))
                .collect(Collectors.toList());
    }
}