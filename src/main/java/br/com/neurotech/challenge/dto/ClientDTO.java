package br.com.neurotech.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Client eligible for Hatch vehicle credit")
public class ClientDTO {
    @Schema(description = "Full name of the client", example = "John Doe")
    private String name;

    @Schema(description = "Monthly income of the client", example = "10000.00")
    private Double income;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}