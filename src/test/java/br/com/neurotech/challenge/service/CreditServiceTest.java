package br.com.neurotech.challenge.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.neurotech.challenge.constants.CreditConstants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.impl.CreditServiceImpl;

@SpringBootTest
public class CreditServiceTest {

    @Autowired
    private CreditService creditService;

    @MockBean
    private ClientService clientService;

    @InjectMocks
    private CreditServiceImpl creditServiceImpl;

    private NeurotechClient eligibleClientForHatch;
    private NeurotechClient ineligibleClientForHatch;
    private NeurotechClient eligibleClientForSUV;
    private NeurotechClient ineligibleClientForSUV;

    @BeforeEach
    public void setUp() {
        // Eligible client for Hatch (age 23-49, income 5000-15000)
        eligibleClientForHatch = new NeurotechClient();
        eligibleClientForHatch.setId("1");
        eligibleClientForHatch.setName("John Doe");
        eligibleClientForHatch.setAge(CreditConstants.HATCH_MIN_AGE + 5);
        eligibleClientForHatch.setIncome(10000.0);

        // Ineligible client for Hatch (age < 23)
        ineligibleClientForHatch = new NeurotechClient();
        ineligibleClientForHatch.setId("2");
        ineligibleClientForHatch.setName("Jane Smith");
        ineligibleClientForHatch.setAge(CreditConstants.HATCH_MIN_AGE - 1);
        ineligibleClientForHatch.setIncome(5000.0);

        // Eligible client for SUV (age >= 21, income >= 8000)
        eligibleClientForSUV = new NeurotechClient();
        eligibleClientForSUV.setId("3");
        eligibleClientForSUV.setName("Bob Wilson");
        eligibleClientForSUV.setAge(CreditConstants.SUV_MIN_AGE + 5);
        eligibleClientForSUV.setIncome(12000.0);

        // Ineligible client for SUV (income < 8000)
        ineligibleClientForSUV = new NeurotechClient();
        ineligibleClientForSUV.setId("4");
        ineligibleClientForSUV.setName("Alice Brown");
        ineligibleClientForSUV.setAge(CreditConstants.SUV_MIN_AGE + 5);
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