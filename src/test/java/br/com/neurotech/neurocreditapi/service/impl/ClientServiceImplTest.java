package br.com.neurotech.neurocreditapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.exception.ClientAlreadyExistsException;
import br.com.neurotech.neurocreditapi.repository.ClientRepository;

@SpringBootTest
public class ClientServiceImplTest {

    @Autowired
    private ClientServiceImpl clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    void testSaveClient() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(5000.0);

        when(clientRepository.save(any(NeurotechClient.class))).thenReturn(client);

        // When
        String clientId = clientService.save(client);

        // Then
        assertNotNull(clientId);
    }

    @Test
    void testSaveClient_WithExistingId_ThrowsException() {
        // Given
        String existingId = "existing-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(existingId);
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(5000.0);

        when(clientRepository.existsById(existingId)).thenReturn(true);

        // When/Then
        assertThrows(ClientAlreadyExistsException.class, () -> clientService.save(client));
    }

    @Test
    void testSaveClient_WithProvidedId_SavesSuccessfully() {
        // Given
        String providedId = "provided-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(providedId);
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(5000.0);

        when(clientRepository.existsById(providedId)).thenReturn(false);
        when(clientRepository.save(any(NeurotechClient.class))).thenReturn(client);

        // When
        String savedClientId = clientService.save(client);

        // Then
        assertEquals(providedId, savedClientId);
    }

    @Test
    void testGetClient() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setName("Jane Doe");
        client.setAge(30);
        client.setIncome(10000.0);
        String clientId = "test-id";
        client.setId(clientId);

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // When
        Optional<NeurotechClient> retrievedClientOpt = clientService.get(clientId);

        // Then
        assertTrue(retrievedClientOpt.isPresent());
        NeurotechClient retrievedClient = retrievedClientOpt.get();
        assertEquals(client.getName(), retrievedClient.getName());
        assertEquals(client.getAge(), retrievedClient.getAge());
        assertEquals(client.getIncome(), retrievedClient.getIncome());
    }
}