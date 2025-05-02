package br.com.neurotech.challenge.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;

@WebMvcTest(CreditController.class)
public class CreditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditService creditService;

    @Test
    void testCheckCreditEligible() throws Exception {
        // Given
        String clientId = "test-client-id";
        VehicleModel vehicleModel = VehicleModel.HATCH;
        when(creditService.checkCredit(clientId, vehicleModel)).thenReturn(true);

        // When/Then
        mockMvc.perform(get("/api/credit/{clientId}/{vehicleModel}", clientId, vehicleModel))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void testCheckCreditNotEligible() throws Exception {
        // Given
        String clientId = "test-client-id";
        VehicleModel vehicleModel = VehicleModel.SUV;
        when(creditService.checkCredit(clientId, vehicleModel)).thenReturn(false);

        // When/Then
        mockMvc.perform(get("/api/credit/{clientId}/{vehicleModel}", clientId, vehicleModel))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void testCheckCreditInvalidVehicleModel() throws Exception {
        // Given
        String clientId = "test-client-id";
        String invalidVehicleModel = "INVALID";

        // When/Then
        mockMvc.perform(get("/api/credit/{clientId}/{vehicleModel}", clientId, invalidVehicleModel))
                .andExpect(status().isBadRequest());
    }
} 