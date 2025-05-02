package br.com.neurotech.challenge.service.impl;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.CreditType;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;

@Service
public class CreditServiceImpl implements CreditService {

    private final ClientService clientService;

    public CreditServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean checkCredit(String clientId, VehicleModel model) {
        NeurotechClient client = clientService.get(clientId);
        if (client == null) {
            return false;
        }

        int age = client.getAge();
        double income = client.getIncome();

        // First check if client is eligible for any credit type
        if (age < CreditType.FIXED_INTEREST.getMinAge()) {
            return false; 
        }

        // Check vehicle-specific requirements
        if (VehicleModel.HATCH.equals(model)) {
            // For Hatch, client must be eligible for Variable Interest Credit
            return age >= CreditType.VARIABLE_INTEREST.getMinAge() && 
                   age <= CreditType.VARIABLE_INTEREST.getMaxAge() &&
                   income >= CreditType.VARIABLE_INTEREST.getMinIncome() &&
                   income <= CreditType.VARIABLE_INTEREST.getMaxIncome();
        } else if (VehicleModel.SUV.equals(model)) {
            // For SUV, client must be eligible for Variable Interest Credit and be over 20
            return age > 20 && 
                   age >= CreditType.VARIABLE_INTEREST.getMinAge() && 
                   age <= CreditType.VARIABLE_INTEREST.getMaxAge() &&
                   income >= 8000.0; // SUV requires higher income
        }

        return false;
    }

} 