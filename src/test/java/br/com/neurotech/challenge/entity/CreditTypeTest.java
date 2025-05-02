package br.com.neurotech.challenge.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CreditTypeTest {

    @Test
    void testCreditTypeValues() {
        // Verify all required credit types exist
        CreditType[] types = CreditType.values();
        assertEquals(3, types.length, "Should have exactly 3 credit types");

        // Verify FIXED_INTEREST properties
        CreditType fixedInterest = CreditType.FIXED_INTEREST;
        assertNotNull(fixedInterest);
        assertEquals(5.0, fixedInterest.getInterestRate(), "Fixed interest rate should be 5%");
        assertEquals(18, fixedInterest.getMinAge(), "Fixed interest min age should be 18");
        assertEquals(25, fixedInterest.getMaxAge(), "Fixed interest max age should be 25");

        // Verify VARIABLE_INTEREST properties
        CreditType variableInterest = CreditType.VARIABLE_INTEREST;
        assertNotNull(variableInterest);
        assertEquals(21, variableInterest.getMinAge(), "Variable interest min age should be 21");
        assertEquals(65, variableInterest.getMaxAge(), "Variable interest max age should be 65");
        assertEquals(5000.0, variableInterest.getMinIncome(), "Variable interest min income should be 5000");
        assertEquals(15000.0, variableInterest.getMaxIncome(), "Variable interest max income should be 15000");

        // Verify CONSIGNED properties
        CreditType consigned = CreditType.CONSIGNED;
        assertNotNull(consigned);
        assertEquals(65, consigned.getMinAge(), "Consigned min age should be 65");
    }
} 