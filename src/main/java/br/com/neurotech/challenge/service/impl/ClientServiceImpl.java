package br.com.neurotech.challenge.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.repository.ClientRepository;
import br.com.neurotech.challenge.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public String save(NeurotechClient client) {
        if (client.getId() == null) {
            client.setId(UUID.randomUUID().toString());
        }
        return clientRepository.save(client).getId();
    }

    @Override
    public Optional<NeurotechClient> get(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<NeurotechClient> getAll() {
        return clientRepository.findAll();
    }
}