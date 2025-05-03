package br.com.neurotech.neurocreditapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.neurotech.neurocreditapi.entity.NeurotechClient;

@Service
public interface ClientService {

	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 */
	String save(NeurotechClient client);

	/**
	 * Recupera um cliente baseado no seu ID
	 */
	Optional<NeurotechClient> get(String id);

	List<NeurotechClient> getAll();
}
