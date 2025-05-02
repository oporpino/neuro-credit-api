package br.com.neurotech.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Vehicle model types")
public enum VehicleModelDTO {
    @Schema(description = "Hatchback vehicle model")
    HATCH,

    @Schema(description = "SUV vehicle model")
    SUV
}