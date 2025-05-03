package br.com.neurotech.neurocreditapi.mapper;

import br.com.neurotech.neurocreditapi.dto.ClientDTO;
import br.com.neurotech.neurocreditapi.dto.ClientRequestDTO;
import br.com.neurotech.neurocreditapi.entity.NeurotechClient;

public interface ClientMapper {
    NeurotechClient toEntity(ClientRequestDTO dto);

    ClientDTO toDTO(NeurotechClient entity);

    ClientRequestDTO toRequestDTO(NeurotechClient entity);
}