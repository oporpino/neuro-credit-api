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

import br.com.neurotech.neurocreditapi.config.TestConstants;
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
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_5000);

        // When
        when(clientRepository.save(any(NeurotechClient.class))).thenReturn(client);

        // Then
        String clientId = clientService.save(client);
        assertNotNull(clientId);
    }

    @Test
    void testSaveClient_WithExistingId_ThrowsException() {
        // Given
        String existingId = "existing-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(existingId);
        client.setName("John Doe");
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_5000);

        // When/Then
        when(clientRepository.existsById(existingId)).thenReturn(true);
        assertThrows(ClientAlreadyExistsException.class, () -> clientService.save(client));
    }

    @Test
    void testSaveClient_WithProvidedId_SavesSuccessfully() {
        // Given
        String providedId = "provided-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(providedId);
        client.setName("John Doe");
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_5000);

        // When
        when(clientRepository.existsById(providedId)).thenReturn(false);
        when(clientRepository.save(any(NeurotechClient.class))).thenReturn(client);

        // Then
        String savedClientId = clientService.save(client);
        assertEquals(providedId, savedClientId);
    }

    @Test
    void testGetClient() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setName("Jane Doe");
        client.setAge(TestConstants.Client.AGE_30);
        client.setIncome(TestConstants.Client.INCOME_10000);
        String clientId = "test-id";
        client.setId(clientId);

        // When
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // Then
        Optional<NeurotechClient> retrievedClientOpt = clientService.get(clientId);
        assertTrue(retrievedClientOpt.isPresent());
        NeurotechClient retrievedClient = retrievedClientOpt.get();
        assertEquals(client.getName(), retrievedClient.getName());
        assertEquals(client.getAge(), retrievedClient.getAge());
        assertEquals(client.getIncome(), retrievedClient.getIncome());
    }
}