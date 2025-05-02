package br.com.neurotech.challenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.CreditTypeFactory;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.entity.credit.Credit;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;

@Service
public class CreditServiceImpl implements CreditService {
    private final ClientService clientService;
    private static final int HATCH_MIN_AGE = 23;
    private static final int HATCH_MAX_AGE = 49;

    public CreditServiceImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean checkCredit(String clientId, VehicleModel model) {
        NeurotechClient client = clientService.get(clientId);
        if (client == null) {
            return false;
        }

        Credit applicableCredit = CreditTypeFactory.getApplicableCredit(client, model);
        if (applicableCredit == null) {
            return false;
        }

        return applicableCredit.isEligible();
    }

    @Override
    public List<NeurotechClient> findEligibleClientsForHatch() {
        return clientService.getAll().stream()
                .filter(client -> {
                    Credit credit = CreditTypeFactory.getApplicableCredit(client, VehicleModel.HATCH);
                    return credit != null && credit.isEligible();
                })
                .collect(Collectors.toList());
    }
}