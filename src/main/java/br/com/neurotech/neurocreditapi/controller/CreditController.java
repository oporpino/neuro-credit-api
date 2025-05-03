package br.com.neurotech.neurocreditapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neurotech.neurocreditapi.dto.ClientDTO;
import br.com.neurotech.neurocreditapi.dto.VehicleModelDTO;
import br.com.neurotech.neurocreditapi.entity.VehicleModel;
import br.com.neurotech.neurocreditapi.mapper.ClientMapper;
import br.com.neurotech.neurocreditapi.mapper.VehicleModelMapper;
import br.com.neurotech.neurocreditapi.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/credit", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Credit Evaluation", description = "Endpoints for credit evaluation and eligibility")
public class CreditController {

    private final CreditService creditService;
    private final ClientMapper clientMapper;
    private final VehicleModelMapper vehicleModelMapper;

    @Autowired
    public CreditController(CreditService creditService, ClientMapper clientMapper,
            VehicleModelMapper vehicleModelMapper) {
        this.creditService = creditService;
        this.clientMapper = clientMapper;
        this.vehicleModelMapper = vehicleModelMapper;
    }

    @GetMapping("/{clientId}/{vehicleModel}")
    @Operation(summary = "Check credit eligibility", description = "Checks if a client is eligible for credit for a specific vehicle model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eligibility check completed"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Boolean> checkCredit(
            @Parameter(description = "ID of the client to check", required = true) @PathVariable String clientId,
            @Parameter(description = "Vehicle model to check eligibility for", required = true) @PathVariable VehicleModelDTO vehicleModel) {
        VehicleModel model = vehicleModelMapper.toEntity(vehicleModel);
        boolean isEligible = creditService.checkCredit(clientId, model);
        return ResponseEntity.ok(isEligible);
    }

    @GetMapping("/clients/hatch")
    @Operation(summary = "Get eligible clients for Hatch", description = "Returns a list of clients eligible for Hatch vehicle credit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of eligible clients retrieved successfully")
    })
    public ResponseEntity<List<ClientDTO>> getEligibleClientsForHatch() {
        List<ClientDTO> eligibleClients = creditService.findEligibleClientsForHatch()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eligibleClients);
    }
}