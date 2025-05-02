package br.com.neurotech.challenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

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

        if (age < CreditType.FIXED_INTEREST.getMinAge()) {
            return false; 
        }

        if (VehicleModel.HATCH.equals(model)) {
            
            return age >= CreditType.VARIABLE_INTEREST.getMinAge() && 
                   age <= CreditType.VARIABLE_INTEREST.getMaxAge() &&
                   income >= CreditType.VARIABLE_INTEREST.getMinIncome() &&
                   income <= CreditType.VARIABLE_INTEREST.getMaxIncome();
        } else if (VehicleModel.SUV.equals(model)) {
            
            return age > 20 && 
                   age >= CreditType.VARIABLE_INTEREST.getMinAge() && 
                   age <= CreditType.VARIABLE_INTEREST.getMaxAge() &&
                   income >= 8000.0; // TODO: SUV requires higher income extract to constant
        }

        return false;
    }

    @Override
    public List<NeurotechClient> findEligibleClientsForHatch() {
        return clientService.getAll().stream()
            .filter(client -> {
                int age = client.getAge();
                double income = client.getIncome();
                
                return age >= 23 && 
                       age <= 49 && 
                       income >= CreditType.VARIABLE_INTEREST.getMinIncome() && 
                       income <= CreditType.VARIABLE_INTEREST.getMaxIncome();
            })
            .collect(Collectors.toList());
    }
} 