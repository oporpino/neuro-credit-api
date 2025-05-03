package br.com.neurotech.challenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Client information for credit evaluation")
public class ClientDTO {
    @Schema(description = "Unique identifier of the client", example = "client123")
    private String id;

    @Schema(description = "Full name of the client", example = "John Doe")
    private String name;

    @Schema(description = "Age of the client", example = "25")
    private Integer age;

    @Schema(description = "Monthly income of the client", example = "10000.00")
    private Double income;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }
}