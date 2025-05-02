package br.com.neurotech.challenge.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Client information")
public class NeurotechClient {
	@Schema(description = "Unique identifier of the client", example = "client123")
	private String id;

	@Schema(description = "Full name of the client", example = "John Doe")
	private String name;

	@Schema(description = "Age of the client", example = "25")
	private Integer age;

	@Schema(description = "Monthly income of the client", example = "10000.00")
	private Double income;
}