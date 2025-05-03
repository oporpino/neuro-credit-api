package br.com.neurotech.neurocreditapi.mapper.impl;

import org.springframework.stereotype.Component;

import br.com.neurotech.neurocreditapi.dto.VehicleModelDTO;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;
import br.com.neurotech.neurocreditapi.mapper.VehicleModelMapper;

@Component
public class VehicleModelMapperImpl implements VehicleModelMapper {

    @Override
    public VehicleModel toEntity(VehicleModelDTO dto) {
        if (dto == null) {
            return null;
        }
        return VehicleModel.valueOf(dto.name());
    }

    @Override
    public VehicleModelDTO toDTO(VehicleModel entity) {
        if (entity == null) {
            return null;
        }
        return VehicleModelDTO.valueOf(entity.name());
    }
}