package br.com.neurotech.challenge.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.neurotech.challenge.constants.CreditConstants;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.ClientService;

@ExtendWith(MockitoExtension.class)
public class CreditServiceImplTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private CreditServiceImpl creditService;

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
    public void testCheckCredit_EligibleForHatch_ReturnsTrue() {
        when(clientService.get("1")).thenReturn(eligibleClientForHatch);
        assertTrue(creditService.checkCredit("1", VehicleModel.HATCH));
    }

    @Test
    public void testCheckCredit_IneligibleForHatch_ReturnsFalse() {
        when(clientService.get("2")).thenReturn(ineligibleClientForHatch);
        assertFalse(creditService.checkCredit("2", VehicleModel.HATCH));
    }

    @Test
    public void testCheckCredit_EligibleForSUV_ReturnsTrue() {
        when(clientService.get("3")).thenReturn(eligibleClientForSUV);
        assertTrue(creditService.checkCredit("3", VehicleModel.SUV));
    }

    @Test
    public void testCheckCredit_IneligibleForSUV_ReturnsFalse() {
        when(clientService.get("4")).thenReturn(ineligibleClientForSUV);
        assertFalse(creditService.checkCredit("4", VehicleModel.SUV));
    }

    @Test
    public void testCheckCredit_ClientNotFound_ReturnsFalse() {
        when(clientService.get("5")).thenReturn(null);
        assertFalse(creditService.checkCredit("5", VehicleModel.HATCH));
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