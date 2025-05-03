package br.com.neurotech.neurocreditapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neurotech.neurocreditapi.config.TestConstants;
import br.com.neurotech.neurocreditapi.dto.ClientRequestDTO;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.exception.ClientAlreadyExistsException;
import br.com.neurotech.neurocreditapi.mapper.ClientMapper;
import br.com.neurotech.neurocreditapi.service.ClientService;

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
        // Given
        ClientRequestDTO clientDTO = new ClientRequestDTO();
        clientDTO.setName("John Doe");
        clientDTO.setAge(TestConstants.Client.AGE_25);
        clientDTO.setIncome(TestConstants.Client.INCOME_10000);

        NeurotechClient client = new NeurotechClient();
        client.setName("John Doe");
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_10000);

        // When
        when(clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(client);
        when(clientService.save(any(NeurotechClient.class))).thenReturn(TestConstants.Client.ID_123);

        // Then
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost/api/client/" + TestConstants.Client.ID_123));
    }

    @Test
    public void testCreateClient_DuplicateId_ReturnsConflict() throws Exception {
        // Given
        ClientRequestDTO clientDTO = new ClientRequestDTO();
        clientDTO.setId("existing-id");
        clientDTO.setName("John Doe");
        clientDTO.setAge(TestConstants.Client.AGE_25);
        clientDTO.setIncome(TestConstants.Client.INCOME_10000);

        NeurotechClient client = new NeurotechClient();
        client.setId("existing-id");
        client.setName("John Doe");
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_10000);

        // When/Then
        when(clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(client);
        when(clientService.save(any(NeurotechClient.class)))
                .thenThrow(new ClientAlreadyExistsException("existing-id"));

        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(TestConstants.Http.STATUS_409))
                .andExpect(jsonPath("$.error").value("Client already exists"))
                .andExpect(jsonPath("$.message").value("Client with ID existing-id already exists"));
    }

    @Test
    public void testGetClient_ValidId_ReturnsClientDTO() throws Exception {
        // Given
        NeurotechClient client = new NeurotechClient();
        client.setId(TestConstants.Client.ID_123);
        client.setName("John Doe");
        client.setAge(TestConstants.Client.AGE_25);
        client.setIncome(TestConstants.Client.INCOME_10000);

        ClientRequestDTO clientDTO = new ClientRequestDTO();
        clientDTO.setId(TestConstants.Client.ID_123);
        clientDTO.setName("John Doe");
        clientDTO.setAge(TestConstants.Client.AGE_25);
        clientDTO.setIncome(TestConstants.Client.INCOME_10000);

        // When
        when(clientService.get(TestConstants.Client.ID_123)).thenReturn(Optional.of(client));
        when(clientMapper.toRequestDTO(client)).thenReturn(clientDTO);

        // Then
        mockMvc.perform(get("/api/client/" + TestConstants.Client.ID_123))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(TestConstants.Client.ID_123))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(TestConstants.Client.AGE_25))
                .andExpect(jsonPath("$.income").value(TestConstants.Client.INCOME_10000));
    }

    @Test
    public void testGetClient_InvalidId_ReturnsNotFound() throws Exception {
        // Given
        when(clientService.get("invalid")).thenReturn(Optional.empty());

        // When/Then
        mockMvc.perform(get("/api/client/invalid"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateClient_InvalidDTO_ReturnsBadRequest() throws Exception {
        // Given
        ClientRequestDTO clientDTO = new ClientRequestDTO();
        // Missing required fields

        // When/Then
        mockMvc.perform(post("/api/client")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isBadRequest());
    }
}
