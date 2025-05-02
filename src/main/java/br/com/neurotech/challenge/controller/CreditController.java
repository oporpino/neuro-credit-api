package br.com.neurotech.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/credit", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Credit Evaluation", description = "Endpoints for credit evaluation and eligibility")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/{clientId}/{vehicleModel}")
    @Operation(summary = "Check credit eligibility", description = "Checks if a client is eligible for credit for a specific vehicle model")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eligibility check completed"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Boolean> checkCredit(
            @Parameter(description = "ID of the client to check", required = true) @PathVariable String clientId,
            @Parameter(description = "Vehicle model to check eligibility for", required = true) @PathVariable VehicleModel vehicleModel) {
        boolean isEligible = creditService.checkCredit(clientId, vehicleModel);
        return ResponseEntity.ok(isEligible);
    }

    @GetMapping("/clients/hatch")
    @Operation(summary = "Get eligible clients for Hatch", description = "Returns a list of clients eligible for Hatch vehicle credit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of eligible clients retrieved successfully")
    })
    public ResponseEntity<List<NeurotechClient>> getEligibleClientsForHatch() {
        List<NeurotechClient> eligibleClients = creditService.findEligibleClientsForHatch();
        return ResponseEntity.ok(eligibleClients);
    }
}