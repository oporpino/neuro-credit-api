package br.com.neurotech.challenge.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.dto.VehicleModelDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.mapper.ClientMapper;
import br.com.neurotech.challenge.mapper.VehicleModelMapper;
import br.com.neurotech.challenge.service.CreditService;

@WebMvcTest(CreditController.class)
public class CreditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditService creditService;

    @MockBean
    private ClientMapper clientMapper;

    @MockBean
    private VehicleModelMapper vehicleModelMapper;

    @Test
    public void testCheckCredit_Eligible_ReturnsTrue() throws Exception {
        when(vehicleModelMapper.toEntity(any(VehicleModelDTO.class))).thenReturn(VehicleModel.HATCH);
        when(creditService.checkCredit("1", VehicleModel.HATCH)).thenReturn(true);

        mockMvc.perform(get("/api/credit/1/HATCH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(true));
    }

    @Test
    public void testCheckCredit_NotEligible_ReturnsFalse() throws Exception {
        when(vehicleModelMapper.toEntity(any(VehicleModelDTO.class))).thenReturn(VehicleModel.HATCH);
        when(creditService.checkCredit("1", VehicleModel.HATCH)).thenReturn(false);

        mockMvc.perform(get("/api/credit/1/HATCH"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(false));
    }

    @Test
    public void testGetEligibleClientsForHatch_ReturnsClients() throws Exception {
        NeurotechClient client = new NeurotechClient();
        client.setId("1");
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(10000.0);

        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId("1");
        clientDTO.setName("John Doe");
        clientDTO.setAge(25);
        clientDTO.setIncome(10000.0);

        List<NeurotechClient> clients = Arrays.asList(client);
        when(creditService.findEligibleClientsForHatch()).thenReturn(clients);
        when(clientMapper.toDTO(client)).thenReturn(clientDTO);

        mockMvc.perform(get("/api/credit/clients/hatch"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].age").value(25))
                .andExpect(jsonPath("$[0].income").value(10000.0));
    }

    @Test
    public void testCheckCredit_InvalidVehicleModel_ReturnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/credit/1/INVALID"))
                .andExpect(status().isBadRequest());
    }
}