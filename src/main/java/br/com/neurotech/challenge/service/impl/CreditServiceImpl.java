package br.com.neurotech.challenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.config.Constants;
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
        return clientService.get(clientId)
                .map(client -> isEligibleForVehicle(client, vehicleModel))
                .orElse(false);
    }

    private boolean isEligibleForVehicle(NeurotechClient client, VehicleModel vehicleModel) {
        int age = client.getAge();
        double income = client.getIncome();

        if (vehicleModel == VehicleModel.HATCH) {
            return age >= Constants.Vehicle.HATCH_MIN_AGE &&
                    age <= Constants.Vehicle.HATCH_MAX_AGE &&
                    income >= Constants.Income.MIN &&
                    income <= Constants.Income.MAX;
        } else if (vehicleModel == VehicleModel.SUV) {
            return age >= Constants.Vehicle.SUV_MIN_AGE &&
                    age <= Constants.Credit.Variable.MAX_AGE &&
                    income >= Constants.Income.SUV_MIN;
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