public class QuantityMeasurementApp {

    // Enum for Length Units
    enum LengthUnit {
        FEET(1.0),          // base unit
        INCH(1.0 / 12.0);   // 1 inch = 1/12 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class
    static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        // Convert to base unit (feet)
        private double toFeet() {
            return unit.toFeet(value);
        }

        // Override equals()
        @Override
        public boolean equals(Object obj) {

            // Same reference
            if (this == obj) return true;

            // Null & type check
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting to common unit (feet)
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method for testing
    public static void main(String[] args) {

        // Same unit comparison (Feet)
        Quantity q1 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q2 = new Quantity(1.0, LengthUnit.FEET);

        System.out.println("Input: Quantity(1.0, FEET) and Quantity(1.0, FEET)");
        System.out.println("Output: Equal (" + q1.equals(q2) + ")");

        // Same unit comparison (Inch)
        Quantity q3 = new Quantity(1.0, LengthUnit.INCH);
        Quantity q4 = new Quantity(1.0, LengthUnit.INCH);

        System.out.println("\nInput: Quantity(1.0, INCH) and Quantity(1.0, INCH)");
        System.out.println("Output: Equal (" + q3.equals(q4) + ")");

        // Cross-unit comparison (Feet vs Inches)
        Quantity q5 = new Quantity(1.0, LengthUnit.FEET);
        Quantity q6 = new Quantity(12.0, LengthUnit.INCH);

        System.out.println("\nInput: Quantity(1.0, FEET) and Quantity(12.0, INCH)");
        System.out.println("Output: Equal (" + q5.equals(q6) + ")");

        // Different values
        Quantity q7 = new Quantity(2.0, LengthUnit.FEET);

        System.out.println("\n1.0 ft vs 2.0 ft: " + q1.equals(q7));

        // Null comparison
        System.out.println("1.0 ft vs null: " + q1.equals(null));

        // Same reference
        System.out.println("Same reference: " + q1.equals(q1));
    }
}