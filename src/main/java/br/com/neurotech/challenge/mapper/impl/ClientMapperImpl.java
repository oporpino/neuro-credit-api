package br.com.neurotech.challenge.mapper.impl;

import org.springframework.stereotype.Component;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.dto.ClientRequestDTO;
import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.mapper.ClientMapper;

@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public NeurotechClient toEntity(ClientRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        NeurotechClient entity = new NeurotechClient();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setIncome(dto.getIncome());
        return entity;
    }

    @Override
    public ClientDTO toDTO(NeurotechClient entity) {
        if (entity == null) {
            return null;
        }

        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setIncome(entity.getIncome());
        return dto;
    }

    @Override
    public ClientRequestDTO toRequestDTO(NeurotechClient entity) {
        if (entity == null) {
            return null;
        }

        ClientRequestDTO dto = new ClientRequestDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setIncome(entity.getIncome());
        return dto;
    }
}