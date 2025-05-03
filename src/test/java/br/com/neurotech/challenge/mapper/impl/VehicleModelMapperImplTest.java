package br.com.neurotech.challenge.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.neurotech.challenge.dto.VehicleModelDTO;
import br.com.neurotech.challenge.entity.VehicleModel;

@SpringBootTest
public class VehicleModelMapperImplTest {

    @Autowired
    private VehicleModelMapperImpl vehicleModelMapper;

    @Test
    public void testToEntity_ValidDTO_ReturnsEntity() {
        VehicleModelDTO dto = VehicleModelDTO.HATCH;
        VehicleModel entity = vehicleModelMapper.toEntity(dto);
        assertEquals(VehicleModel.HATCH, entity);
    }

    @Test
    public void testToEntity_NullDTO_ReturnsNull() {
        assertNull(vehicleModelMapper.toEntity(null));
    }

    @Test
    public void testToDTO_ValidEntity_ReturnsDTO() {
        VehicleModel entity = VehicleModel.SUV;
        VehicleModelDTO dto = vehicleModelMapper.toDTO(entity);
        assertEquals(VehicleModelDTO.SUV, dto);
    }

    @Test
    public void testToDTO_NullEntity_ReturnsNull() {
        assertNull(vehicleModelMapper.toDTO(null));
    }
}