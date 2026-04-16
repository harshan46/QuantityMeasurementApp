public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    static class Feet {
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Override equals() method
        @Override
        public boolean equals(Object obj) {

            // Same reference check
            if (this == obj) {
                return true;
            }

            // Null and type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Cast safely
            Feet other = (Feet) obj;

            // Compare values using Double.compare
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        Feet feet3 = new Feet(2.0);

        System.out.println("1.0 ft vs 1.0 ft: " + feet1.equals(feet2)); // true
        System.out.println("1.0 ft vs 2.0 ft: " + feet1.equals(feet3)); // false
        System.out.println("1.0 ft vs null: " + feet1.equals(null));   // false
        System.out.println("Same reference: " + feet1.equals(feet1));  // true
    }
}