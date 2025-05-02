package br.com.neurotech.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;

class CreditServiceImplTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private CreditServiceImpl creditService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckCreditEligible() {
        // Given
        String clientId = "test-client-id";
        VehicleModel vehicleModel = VehicleModel.HATCH;
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setAge(25);
        client.setIncome(10000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When
        boolean result = creditService.checkCredit(clientId, vehicleModel);

        // Then
        assertTrue(result);
    }

    @Test
    void testCheckCreditNotEligible() {
        // Given
        String clientId = "test-client-id";
        VehicleModel vehicleModel = VehicleModel.SUV;
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setAge(20); // Below SUV_MIN_AGE (21)
        client.setIncome(7000.0); // Below SUV_MIN_INCOME (8000.0)

        when(clientService.get(clientId)).thenReturn(client);

        // When
        boolean result = creditService.checkCredit(clientId, vehicleModel);

        // Then
        assertFalse(result);
    }

    @Test
    void testCheckCreditEligibleForSUV() {
        // Given
        String clientId = "test-client-id";
        VehicleModel vehicleModel = VehicleModel.SUV;
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setAge(25);
        client.setIncome(10000.0);

        when(clientService.get(clientId)).thenReturn(client);

        // When
        boolean result = creditService.checkCredit(clientId, vehicleModel);

        // Then
        assertTrue(result);
    }

    @Test
    void testCheckCreditNotEligibleForSUV() {
        // Given
        String clientId = "test-client-id";
        VehicleModel vehicleModel = VehicleModel.SUV;
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setAge(20); // Below SUV_MIN_AGE (21)
        client.setIncome(7000.0); // Below SUV_MIN_INCOME (8000.0)

        when(clientService.get(clientId)).thenReturn(client);

        // When
        boolean result = creditService.checkCredit(clientId, vehicleModel);

        // Then
        assertFalse(result);
    }

    @Test
    void testFindEligibleClientsForHatch() {
        // Given
        NeurotechClient client1 = new NeurotechClient();
        client1.setName("John Doe");
        client1.setAge(25);
        client1.setIncome(10000.0);

        NeurotechClient client2 = new NeurotechClient();
        client2.setName("Jane Smith");
        client2.setAge(30);
        client2.setIncome(12000.0);

        List<NeurotechClient> clients = Arrays.asList(client1, client2);
        when(clientService.getAll()).thenReturn(clients);

        // When
        List<ClientDTO> result = creditService.findEligibleClientsForHatch();

        // Then
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(10000.0, result.get(0).getIncome());
        assertEquals("Jane Smith", result.get(1).getName());
        assertEquals(12000.0, result.get(1).getIncome());
    }
}