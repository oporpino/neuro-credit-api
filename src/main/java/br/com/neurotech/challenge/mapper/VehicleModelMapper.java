package br.com.neurotech.challenge.mapper;

import br.com.neurotech.challenge.dto.VehicleModelDTO;
import br.com.neurotech.challenge.entity.VehicleModel;

public interface VehicleModelMapper {
    VehicleModel toEntity(VehicleModelDTO dto);

    VehicleModelDTO toDTO(VehicleModel entity);
}