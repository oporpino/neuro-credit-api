package br.com.neurotech.neurocreditapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.exception.ClientAlreadyExistsException;
import br.com.neurotech.neurocreditapi.repository.ClientRepository;
import br.com.neurotech.neurocreditapi.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String save(NeurotechClient client) {
        if (client.getId() != null && clientRepository.existsById(client.getId())) {
            throw new ClientAlreadyExistsException(client.getId());
        }

        if (client.getId() == null) {
            client.setId(UUID.randomUUID().toString());
        }

        NeurotechClient savedClient = clientRepository.save(client);
        return savedClient.getId();
    }

    @Override
    public Optional<NeurotechClient> get(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean exists(String id) {
        return clientRepository.existsById(id);
    }

    @Override
    public List<NeurotechClient> getAll() {
        return clientRepository.findAll();
    }
}