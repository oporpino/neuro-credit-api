package br.com.neurotech.challenge.service.impl;

import org.springframework.stereotype.Service;

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

        switch (model) {
            case HATCH:
                return client.getIncome() >= 5000.0 && client.getIncome() <= 15000.0;
            case SUV:
                return client.getIncome() > 8000.0 && client.getAge() > 20;
            default:
                return false;
        }
    }
} 