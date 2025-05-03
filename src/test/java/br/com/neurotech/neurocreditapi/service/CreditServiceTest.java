package br.com.neurotech.neurocreditapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.neurotech.neurocreditapi.config.Constants;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;
import br.com.neurotech.neurocreditapi.service.impl.CreditServiceImpl;

@SpringBootTest
public class CreditServiceTest {

    @InjectMocks
    private CreditServiceImpl creditService;

    @Mock
    private ClientService clientService;

    private NeurotechClient eligibleClientForHatch;
    private NeurotechClient ineligibleClientForHatch;
    private NeurotechClient eligibleClientForSUV;
    private NeurotechClient ineligibleClientForSUV;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        eligibleClientForHatch = new NeurotechClient();
        eligibleClientForHatch.setId("1");
        eligibleClientForHatch.setName("John Doe");
        eligibleClientForHatch.setAge(Constants.Vehicle.HATCH_MIN_AGE + 5);
        eligibleClientForHatch.setIncome(10000.0);

        ineligibleClientForHatch = new NeurotechClient();
        ineligibleClientForHatch.setId("2");
        ineligibleClientForHatch.setName("Jane Smith");
        ineligibleClientForHatch.setAge(Constants.Vehicle.HATCH_MIN_AGE - 1);
        ineligibleClientForHatch.setIncome(5000.0);

        eligibleClientForSUV = new NeurotechClient();
        eligibleClientForSUV.setId("3");
        eligibleClientForSUV.setName("Bob Wilson");
        eligibleClientForSUV.setAge(Constants.Vehicle.SUV_MIN_AGE + 5);
        eligibleClientForSUV.setIncome(12000.0);

        ineligibleClientForSUV = new NeurotechClient();
        ineligibleClientForSUV.setId("4");
        ineligibleClientForSUV.setName("Alice Brown");
        ineligibleClientForSUV.setAge(Constants.Vehicle.SUV_MIN_AGE + 5);
        ineligibleClientForSUV.setIncome(7000.0);
    }

    @Test
    void testCheckCredit_EligibleForHatch_ReturnsTrue() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setAge(25);
        client.setIncome(5000.0);

        when(clientService.get("123")).thenReturn(Optional.of(client));

        // When
        boolean result = creditService.checkCredit("123", VehicleModel.HATCH);

        // Then
        assertTrue(result);
    }

    @Test
    void testCheckCredit_IneligibleForHatch_ReturnsFalse() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setAge(17);
        client.setIncome(5000.0);

        when(clientService.get("123")).thenReturn(Optional.of(client));

        // When
        boolean result = creditService.checkCredit("123", VehicleModel.HATCH);

        // Then
        assertFalse(result);
    }

    @Test
    void testCheckCredit_EligibleForSUV_ReturnsTrue() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setAge(35);
        client.setIncome(15000.0);

        when(clientService.get("123")).thenReturn(Optional.of(client));

        // When
        boolean result = creditService.checkCredit("123", VehicleModel.SUV);

        // Then
        assertTrue(result);
    }

    @Test
    void testCheckCredit_IneligibleForSUV_ReturnsFalse() {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setAge(35);
        client.setIncome(5000.0);

        when(clientService.get("123")).thenReturn(Optional.of(client));

        // When
        boolean result = creditService.checkCredit("123", VehicleModel.SUV);

        // Then
        assertFalse(result);
    }

    @Test
    void testCheckCredit_ClientNotFound_ReturnsFalse() {
        // Given
        when(clientService.get("invalid")).thenReturn(Optional.empty());

        // When
        boolean result = creditService.checkCredit("invalid", VehicleModel.HATCH);

        // Then
        assertFalse(result);
    }

    @Test
    public void testFindEligibleClientsForHatch_ReturnsEligibleClients() {
        List<NeurotechClient> allClients = Arrays.asList(
                eligibleClientForHatch,
                ineligibleClientForHatch,
                eligibleClientForSUV,
                ineligibleClientForSUV);

        when(clientService.getAll()).thenReturn(allClients);

        List<NeurotechClient> eligibleClients = creditService.findEligibleClientsForHatch();
        assertEquals(3, eligibleClients.size(),
                "Three clients should be eligible for Hatch: eligibleClientForHatch, eligibleClientForSUV, and ineligibleClientForSUV");
        assertTrue(eligibleClients.contains(eligibleClientForHatch), "Should contain eligibleClientForHatch");
        assertTrue(eligibleClients.contains(eligibleClientForSUV), "Should contain eligibleClientForSUV");
        assertTrue(eligibleClients.contains(ineligibleClientForSUV), "Should contain ineligibleClientForSUV");
    }
}