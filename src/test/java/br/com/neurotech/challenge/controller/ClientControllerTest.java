package br.com.neurotech.challenge.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.service.ClientService;
import br.com.neurotech.challenge.service.CreditService;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @MockBean
    private CreditService creditService;

    @Test
    void testCreateClient() throws Exception {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(5000.0);

        // When/Then
        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void testGetClient() throws Exception {
        // Given
        String clientId = "test-client-id";
        NeurotechClient client = new NeurotechClient();
        client.setId(clientId);
        client.setName("Test Client");
        client.setAge(25);
        client.setIncome(5000.0);

        // When/Then
        when(clientService.get(clientId)).thenReturn(client);

        mockMvc.perform(get("/api/client/{id}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.name").value("Test Client"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.income").value(5000.0));
    }

}
