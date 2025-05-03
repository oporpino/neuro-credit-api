package br.com.neurotech.challenge.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neurotech.challenge.dto.ClientRequestDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.mapper.ClientMapper;
import br.com.neurotech.challenge.service.ClientService;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @MockBean
    private ClientMapper clientMapper;

    @Test
    public void testCreateClient_ValidDTO_ReturnsCreated() throws Exception {
        ClientRequestDTO clientDTO = new ClientRequestDTO();
        clientDTO.setName("John Doe");
        clientDTO.setAge(25);
        clientDTO.setIncome(10000.0);

        NeurotechClient client = new NeurotechClient();
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(10000.0);

        when(clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(client);
        when(clientService.save(any(NeurotechClient.class))).thenReturn("123");

        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetClient_ValidId_ReturnsClientDTO() throws Exception {
        NeurotechClient client = new NeurotechClient();
        client.setId("123");
        client.setName("John Doe");
        client.setAge(25);
        client.setIncome(10000.0);

        ClientRequestDTO clientDTO = new ClientRequestDTO();
        clientDTO.setId("123");
        clientDTO.setName("John Doe");
        clientDTO.setAge(25);
        clientDTO.setIncome(10000.0);

        when(clientService.get("123")).thenReturn(client);
        when(clientMapper.toRequestDTO(client)).thenReturn(clientDTO);

        mockMvc.perform(get("/api/client/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("123"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.income").value(10000.0));
    }

    @Test
    public void testCreateClient_InvalidDTO_ReturnsBadRequest() throws Exception {
        ClientRequestDTO clientDTO = new ClientRequestDTO();
        // Missing required fields

        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isBadRequest());
    }
}
