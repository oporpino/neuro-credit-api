package br.com.neurotech.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.CreditService;

@RestController
@RequestMapping("/api/credit")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/{clientId}/{vehicleModel}")
    public ResponseEntity<Boolean> checkCredit(
            @PathVariable String clientId,
            @PathVariable VehicleModel vehicleModel) {
        boolean isEligible = creditService.checkCredit(clientId, vehicleModel);
        return ResponseEntity.ok(isEligible);
    }

    @GetMapping("/clients/hatch")
    public ResponseEntity<List<NeurotechClient>> getEligibleClientsForHatch() {
        List<NeurotechClient> eligibleClients = creditService.findEligibleClientsForHatch();
        return ResponseEntity.ok(eligibleClients);
    }
}