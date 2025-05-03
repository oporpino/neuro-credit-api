package br.com.neurotech.challenge.config;

public final class Constants {
    private Constants() {
        // Utility class, no instantiation
    }

    public static final class Server {
        private Server() {
            // Utility class, no instantiation
        }

        public static final int PORT = 4000;
    }

    public static final class Income {
        private Income() {
            // Utility class, no instantiation
        }

        public static final double MIN = 5000.0;
        public static final double MAX = 15000.0;
        public static final double SUV_MIN = 8000.0;
    }

    public static final class Vehicle {
        private Vehicle() {
            // Utility class, no instantiation
        }

        public static final int HATCH_MIN_AGE = 23;
        public static final int HATCH_MAX_AGE = 49;
        public static final int SUV_MIN_AGE = 21; // Superior a 20 anos
    }

    public static final class Credit {
        private Credit() {
            // Utility class, no instantiation
        }

        public static final class Fixed {
            private Fixed() {
                // Utility class, no instantiation
            }

            public static final int MIN_AGE = 18;
            public static final int MAX_AGE = 25;
        }

        public static final class Variable {
            private Variable() {
                // Utility class, no instantiation
            }

            public static final int MIN_AGE = 21;
            public static final int MAX_AGE = 65;
        }

        public static final class Consigned {
            private Consigned() {
                // Utility class, no instantiation
            }

            public static final int MIN_AGE = 65;
        }
    }
}