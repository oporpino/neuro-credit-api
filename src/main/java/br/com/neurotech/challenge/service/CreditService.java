package br.com.neurotech.challenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.dto.ClientDTO;
import br.com.neurotech.challenge.entity.VehicleModel;

@Service
public interface CreditService {

	/**
	 * Efetua a checagem se o cliente está apto a receber crédito
	 * para um determinado modelo de veículo
	 */
	boolean checkCredit(String clientId, VehicleModel model);

	List<ClientDTO> findEligibleClientsForHatch();

}
