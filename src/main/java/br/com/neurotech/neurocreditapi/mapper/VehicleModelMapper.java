package br.com.neurotech.neurocreditapi.mapper;

import br.com.neurotech.neurocreditapi.dto.VehicleModelDTO;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;

public interface VehicleModelMapper {
    VehicleModel toEntity(VehicleModelDTO dto);

    VehicleModelDTO toDTO(VehicleModel entity);
}