package br.com.neurotech.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.neurotech.challenge.dto.ClientDTO;
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

    @Test
    void testCheckCreditTooYoung() {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(17);
        client.setIncome(10000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        assertFalse(creditService.checkCredit(clientId, VehicleModel.HATCH));
        assertFalse(creditService.checkCredit(clientId, VehicleModel.SUV));
    }

    @Test
    void testFindEligibleClientsForHatch() {
        // Given
        NeurotechClient eligibleClient1 = new NeurotechClient();
        eligibleClient1.setId("client1");
        eligibleClient1.setName("John Doe");
        eligibleClient1.setAge(25);
        eligibleClient1.setIncome(10000.0);

        NeurotechClient eligibleClient2 = new NeurotechClient();
        eligibleClient2.setId("client2");
        eligibleClient2.setName("Jane Smith");
        eligibleClient2.setAge(30);
        eligibleClient2.setIncome(12000.0);

        NeurotechClient ineligibleClient1 = new NeurotechClient();
        ineligibleClient1.setId("client3");
        ineligibleClient1.setName("Bob Wilson");
        ineligibleClient1.setAge(22);
        ineligibleClient1.setIncome(4000.0);

        NeurotechClient ineligibleClient2 = new NeurotechClient();
        ineligibleClient2.setId("client4");
        ineligibleClient2.setName("Alice Brown");
        ineligibleClient2.setAge(50);
        ineligibleClient2.setIncome(10000.0);

        List<NeurotechClient> allClients = Arrays.asList(
                eligibleClient1, eligibleClient2, ineligibleClient1, ineligibleClient2);

        when(clientService.getAll()).thenReturn(allClients);

        // When
        List<ClientDTO> eligibleClients = creditService.findEligibleClientsForHatch();

        // Then
        assertEquals(2, eligibleClients.size());

        ClientDTO dto1 = eligibleClients.get(0);
        assertEquals("John Doe", dto1.getName());
        assertEquals(10000.0, dto1.getIncome());

        ClientDTO dto2 = eligibleClients.get(1);
        assertEquals("Jane Smith", dto2.getName());
        assertEquals(12000.0, dto2.getIncome());
    }
}