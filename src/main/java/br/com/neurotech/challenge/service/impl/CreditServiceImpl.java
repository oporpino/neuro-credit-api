package br.com.neurotech.challenge.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.entity.CreditTypeFactory;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.entity.credit.Credit;
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

        Credit applicableCredit = CreditTypeFactory.getApplicableCredit(client, model);
        if (applicableCredit == null) {
            return false;
        }

        return applicableCredit.isEligible();
    }

    @Override
    public List<ClientDTO> findEligibleClientsForHatch() {
        return clientService.getAll().stream()
                .filter(client -> {
                    Credit credit = CreditTypeFactory.getApplicableCredit(client, VehicleModel.HATCH);
                    return credit != null && credit.isEligible();
                })
                .map(client -> {
                    ClientDTO dto = new ClientDTO();
                    dto.setName(client.getName());
                    dto.setIncome(client.getIncome());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}