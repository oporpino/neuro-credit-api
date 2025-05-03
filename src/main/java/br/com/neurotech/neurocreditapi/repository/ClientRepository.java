package br.com.neurotech.neurocreditapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.neurotech.neurocreditapi.entity.NeurotechClient;

@Repository
public interface ClientRepository extends JpaRepository<NeurotechClient, String> {
}