package br.com.neurotech.neurocreditapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.neurotech.neurocreditapi.entity.NeurotechClient;
import br.com.neurotech.neurocreditapi.exception.ClientAlreadyExistsException;

@Service
public interface ClientService {

	/**
	 * Salva um novo cliente
	 * 
	 * @return ID do cliente recém-salvo
	 * @throws ClientAlreadyExistsException se o cliente com o ID fornecido já
	 *                                      existir
	 */
	String save(NeurotechClient client);

	/**
	 * Recupera um cliente baseado no seu ID
	 */
	Optional<NeurotechClient> get(String id);

	/**
	 * Verifica se um cliente com o ID fornecido já existe
	 */
	boolean exists(String id);

	List<NeurotechClient> getAll();
}
