package br.com.neurotech.challenge.mapper.impl;

import org.springframework.stereotype.Component;

import br.com.neurotech.challenge.dto.VehicleModelDTO;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.mapper.VehicleModelMapper;

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