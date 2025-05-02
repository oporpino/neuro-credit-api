package br.com.neurotech.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.neurotech.challenge.entity.NeurotechClient;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void testSaveClient() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(5000.0);

        // When
        String clientId = clientService.save(client);

        // Then
        assertNotNull(clientId);
    }

    @Test
    void testGetClient() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setName("Jane Doe");
        client.setAge(30);
        client.setIncome(10000.0);
        String clientId = clientService.save(client);

        // When
        NeurotechClient retrievedClient = clientService.get(clientId);

        // Then
        assertNotNull(retrievedClient);
        assertEquals(client.getName(), retrievedClient.getName());
        assertEquals(client.getAge(), retrievedClient.getAge());
        assertEquals(client.getIncome(), retrievedClient.getIncome());
    }
} 