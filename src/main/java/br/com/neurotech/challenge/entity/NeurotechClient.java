package br.com.neurotech.challenge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class NeurotechClient {
	@Id
	private String id;
	private String name;
	private Integer age;
	private Double income;
}