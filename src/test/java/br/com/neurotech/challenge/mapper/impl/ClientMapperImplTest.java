package br.com.neurotech.challenge.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.dto.ClientRequestDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;

@SpringBootTest
public class ClientMapperImplTest {

    @Autowired
    private ClientMapperImpl clientMapper;

    @Test
    public void testToEntity_ValidDTO_ReturnsEntity() {
        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setId("123");
        dto.setName("John Doe");
        dto.setAge(25);
        dto.setIncome(10000.0);

        NeurotechClient entity = clientMapper.toEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getAge(), entity.getAge());
        assertEquals(dto.getIncome(), entity.getIncome());
    }

    @Test
    public void testToEntity_NullDTO_ReturnsNull() {
        assertNull(clientMapper.toEntity(null));
    }

    @Test
    public void testToDTO_ValidEntity_ReturnsDTO() {
        NeurotechClient entity = new NeurotechClient();
        entity.setId("123");
        entity.setName("John Doe");
        entity.setAge(25);
        entity.setIncome(10000.0);

        ClientDTO dto = clientMapper.toDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getAge(), dto.getAge());
        assertEquals(entity.getIncome(), dto.getIncome());
    }

    @Test
    public void testToDTO_NullEntity_ReturnsNull() {
        assertNull(clientMapper.toDTO(null));
    }

    @Test
    public void testToRequestDTO_ValidEntity_ReturnsDTO() {
        NeurotechClient entity = new NeurotechClient();
        entity.setId("123");
        entity.setName("John Doe");
        entity.setAge(25);
        entity.setIncome(10000.0);

        ClientRequestDTO dto = clientMapper.toRequestDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getAge(), dto.getAge());
        assertEquals(entity.getIncome(), dto.getIncome());
    }

    @Test
    public void testToRequestDTO_NullEntity_ReturnsNull() {
        assertNull(clientMapper.toRequestDTO(null));
    }
}