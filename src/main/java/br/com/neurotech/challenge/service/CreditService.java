package br.com.neurotech.challenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;

@Service
public interface CreditService {

	/**
	 * Efetua a checagem se o cliente está apto a receber crédito
	 * para um determinado modelo de veículo
	 */
	boolean checkCredit(String clientId, VehicleModel vehicleModel);

	List<NeurotechClient> findEligibleClientsForHatch();

}
