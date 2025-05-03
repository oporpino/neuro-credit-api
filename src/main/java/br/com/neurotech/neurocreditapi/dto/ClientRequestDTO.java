package br.com.neurotech.neurocreditapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Client information for creation and retrieval")
public class ClientRequestDTO {
    @Schema(description = "Unique identifier of the client", example = "client123")
    private String id;

    @NotBlank(message = "Name is required")
    @Schema(description = "Full name of the client", example = "John Doe")
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Schema(description = "Age of the client", example = "25")
    private Integer age;

    @NotNull(message = "Income is required")
    @Min(value = 0, message = "Income must be positive")
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