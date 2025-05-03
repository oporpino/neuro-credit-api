package br.com.neurotech.challenge.mapper;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.dto.ClientRequestDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;

public interface ClientMapper {
    NeurotechClient toEntity(ClientRequestDTO dto);

    ClientDTO toDTO(NeurotechClient entity);

    ClientRequestDTO toRequestDTO(NeurotechClient entity);
}