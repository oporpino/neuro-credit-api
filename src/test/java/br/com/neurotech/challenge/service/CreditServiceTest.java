package br.com.neurotech.challenge.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.impl.CreditServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CreditServiceTest {

    @Mock
    private ClientService clientService;

    private CreditService creditService;

    @BeforeEach
    void setUp() {
        creditService = new CreditServiceImpl(clientService);
    }

    @Test
    void testCheckCreditHatchEligible() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(10000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertTrue(creditService.checkCredit(clientId, VehicleModel.HATCH));
    }

    @Test
    void testCheckCreditHatchLowerBound() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(5000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertTrue(creditService.checkCredit(clientId, VehicleModel.HATCH));
    }

    @Test
    void testCheckCreditHatchUpperBound() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(15000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertTrue(creditService.checkCredit(clientId, VehicleModel.HATCH));
    }

    @Test
    void testCheckCreditHatchBelowLowerBound() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(4999.99);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertFalse(creditService.checkCredit(clientId, VehicleModel.HATCH));
    }

    @Test
    void testCheckCreditHatchAboveUpperBound() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(15000.01);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertFalse(creditService.checkCredit(clientId, VehicleModel.HATCH));
    }

    @Test
    void testCheckCreditSUVEligible() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(9000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertTrue(creditService.checkCredit(clientId, VehicleModel.SUV));
    }

    @Test
    void testCheckCreditSUVNotEligible() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(19);
        client.setIncome(9000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertFalse(creditService.checkCredit(clientId, VehicleModel.SUV));
    }

    @Test
    void testCheckCreditClientNotFound() {
        // Given
        String clientId = "non-existent-client-id";
        when(clientService.get(clientId)).thenReturn(null);

        // When/Then
        assertFalse(creditService.checkCredit(clientId, VehicleModel.HATCH));
        assertFalse(creditService.checkCredit(clientId, VehicleModel.SUV));
    }
} 