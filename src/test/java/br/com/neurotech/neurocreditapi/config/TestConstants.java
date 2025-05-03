package br.com.neurotech.neurocreditapi.config;

public final class TestConstants {
    private TestConstants() {
        // Utility class, no instantiation
    }

    public static final class Client {
        private Client() {
            // Utility class, no instantiation
        }

        public static final String ID_1 = "1";
        public static final String ID_2 = "2";
        public static final String ID_3 = "3";
        public static final String ID_4 = "4";
        public static final String ID_123 = "123";

        public static final int AGE_25 = 25;
        public static final int AGE_30 = 30;
        public static final int AGE_35 = 35;
        public static final int AGE_17 = 17;

        public static final double INCOME_5000 = 5000.0;
        public static final double INCOME_7000 = 7000.0;
        public static final double INCOME_10000 = 10000.0;
        public static final double INCOME_12000 = 12000.0;
        public static final double INCOME_15000 = 15000.0;
    }

    public static final class Http {
        private Http() {
            // Utility class, no instantiation
        }

        public static final int STATUS_200 = 200;
        public static final int STATUS_201 = 201;
        public static final int STATUS_400 = 400;
        public static final int STATUS_404 = 404;
        public static final int STATUS_409 = 409;
    }
}