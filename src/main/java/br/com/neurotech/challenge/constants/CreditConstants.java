package br.com.neurotech.challenge.constants;

public final class CreditConstants {
    private CreditConstants() {
        // Utility class, no instantiation
    }

    // Server configuration
    public static final int SERVER_PORT = 4000;

    // Income limits
    public static final double MIN_INCOME = 5000.0;
    public static final double MAX_INCOME = 15000.0;
    public static final double SUV_MIN_INCOME = 8000.0;

    // Vehicle eligibility
    public static final int HATCH_MIN_AGE = 23;
    public static final int HATCH_MAX_AGE = 49;
    public static final int SUV_MIN_AGE = 21; // Superior a 20 anos

    // Credit type eligibility
    public static final int FIXED_CREDIT_MIN_AGE = 18;
    public static final int FIXED_CREDIT_MAX_AGE = 25;
    public static final int VARIABLE_CREDIT_MIN_AGE = 21;
    public static final int VARIABLE_CREDIT_MAX_AGE = 65;
    public static final int CONSIGNED_CREDIT_MIN_AGE = 65;
}