package br.com.neurotech.neurocreditapi.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.neurotech.neurocreditapi.config.Constants;
import br.com.neurotech.neurocreditapi.config.TestConstants;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;
import br.com.neurotech.neurocreditapi.service.ClientService;
import br.com.neurotech.neurocreditapi.service.CreditService;

@SpringBootTest
public class CreditServiceImplTest {

    @Autowired
    private CreditService creditService;

    @MockBean
    private ClientService clientService;

    private NeurotechClient eligibleClientForHatch;
    private NeurotechClient ineligibleClientForHatch;
    private NeurotechClient eligibleClientForSUV;
    private NeurotechClient ineligibleClientForSUV;

    @BeforeEach
    public void setUp() {
        // Eligible client for Hatch (age 23-49, income 5000-15000)
        eligibleClientForHatch = new NeurotechClient();
        eligibleClientForHatch.setId(TestConstants.Client.ID_1);
        eligibleClientForHatch.setName("John Doe");
        eligibleClientForHatch.setAge(Constants.Vehicle.HATCH_MIN_AGE + 5);
        eligibleClientForHatch.setIncome(TestConstants.Client.INCOME_10000);

        // Ineligible client for Hatch (age < 23)
        ineligibleClientForHatch = new NeurotechClient();
        ineligibleClientForHatch.setId(TestConstants.Client.ID_2);
        ineligibleClientForHatch.setName("Jane Smith");
        ineligibleClientForHatch.setAge(Constants.Vehicle.HATCH_MIN_AGE - 1);
        ineligibleClientForHatch.setIncome(TestConstants.Client.INCOME_5000);

        // Eligible client for SUV (age >= 21, income >= 8000)
        eligibleClientForSUV = new NeurotechClient();
        eligibleClientForSUV.setId(TestConstants.Client.ID_3);
        eligibleClientForSUV.setName("Bob Wilson");
        eligibleClientForSUV.setAge(Constants.Vehicle.SUV_MIN_AGE + 5);
        eligibleClientForSUV.setIncome(TestConstants.Client.INCOME_12000);

        // Ineligible client for SUV (income < 8000)
        ineligibleClientForSUV = new NeurotechClient();
        ineligibleClientForSUV.setId(TestConstants.Client.ID_4);
        ineligibleClientForSUV.setName("Alice Brown");
        ineligibleClientForSUV.setAge(Constants.Vehicle.SUV_MIN_AGE + 5);
        ineligibleClientForSUV.setIncome(TestConstants.Client.INCOME_7000);
    }

    @Test
    public void testCheckCredit_EligibleForHatch_ReturnsTrue() {
        NeurotechClient client = new NeurotechClient();
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_5000);

        when(clientService.get(TestConstants.Client.ID_123)).thenReturn(Optional.of(client));

        boolean result = creditService.checkCredit(TestConstants.Client.ID_123, VehicleModel.HATCH);
        assertTrue(result);
    }

    @Test
    public void testCheckCredit_IneligibleForHatch_ReturnsFalse() {
        NeurotechClient client = new NeurotechClient();
        client.setAge(TestConstants.Client.AGE_17);
        client.setIncome(TestConstants.Client.INCOME_5000);

        when(clientService.get(TestConstants.Client.ID_123)).thenReturn(Optional.of(client));

        boolean result = creditService.checkCredit(TestConstants.Client.ID_123, VehicleModel.HATCH);
        assertFalse(result);
    }

    @Test
    public void testCheckCredit_EligibleForSUV_ReturnsTrue() {
        NeurotechClient client = new NeurotechClient();
        client.setAge(TestConstants.Client.AGE_35);
        client.setIncome(TestConstants.Client.INCOME_15000);

        when(clientService.get(TestConstants.Client.ID_123)).thenReturn(Optional.of(client));

        boolean result = creditService.checkCredit(TestConstants.Client.ID_123, VehicleModel.SUV);
        assertTrue(result);
    }

    @Test
    public void testCheckCredit_IneligibleForSUV_ReturnsFalse() {
        NeurotechClient client = new NeurotechClient();
        client.setAge(TestConstants.Client.AGE_35);
        client.setIncome(TestConstants.Client.INCOME_5000);

        when(clientService.get(TestConstants.Client.ID_123)).thenReturn(Optional.of(client));

        boolean result = creditService.checkCredit(TestConstants.Client.ID_123, VehicleModel.SUV);
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
        assertEquals(1, eligibleClients.size());
        assertEquals(TestConstants.Client.ID_1, eligibleClients.get(0).getId());
    }
}