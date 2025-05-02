package br.com.neurotech.challenge.service.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    
    // Using an in-memory map to store clients for simplicity
    private final Map<String, NeurotechClient> clients = new ConcurrentHashMap<>();

    @Override
    public String save(NeurotechClient client) {
        String id = UUID.randomUUID().toString();
        clients.put(id, client);
        return id;
    }

    @Override
    public NeurotechClient get(String id) {
        return clients.get(id);
    }
} 