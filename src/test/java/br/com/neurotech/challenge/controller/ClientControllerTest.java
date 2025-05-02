package br.com.neurotech.challenge.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(post("/api/client")
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

        when(clientService.get(clientId)).thenReturn(client);

        // When/Then
        mockMvc.perform(get("/api/client/{id}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(clientId))
                .andExpect(jsonPath("$.name").value(client.getName()))
                .andExpect(jsonPath("$.age").value(client.getAge()))
                .andExpect(jsonPath("$.income").value(client.getIncome()));
    }

}
