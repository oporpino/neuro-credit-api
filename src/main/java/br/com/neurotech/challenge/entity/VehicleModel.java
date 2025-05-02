package br.com.neurotech.challenge.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle model types")
public enum VehicleModel {
	@Schema(description = "Hatchback vehicle model")
	HATCH,

	@Schema(description = "SUV vehicle model")
	SUV
}
