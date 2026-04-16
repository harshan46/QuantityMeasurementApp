public class QuantityMeasurementApp {

    // Enum for all supported units (base unit = FEET)
    enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0),          // 1 inch = 1/12 feet
        YARDS(3.0),                  // 1 yard = 3 feet
        CENTIMETERS(0.393701 / 12.0); // 1 cm = 0.393701 inch → convert to feet

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

        // Equality check
        @Override
        public boolean equals(Object obj) {

            if (this == obj) return true;

            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }
    }

    // Main method for testing
    public static void main(String[] args) {

        // Yard ↔ Feet
        Quantity q1 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q2 = new Quantity(3.0, LengthUnit.FEET);

        System.out.println("1 yard vs 3 feet: " + q1.equals(q2));

        // Yard ↔ Inches
        Quantity q3 = new Quantity(1.0, LengthUnit.YARDS);
        Quantity q4 = new Quantity(36.0, LengthUnit.INCHES);

        System.out.println("1 yard vs 36 inches: " + q3.equals(q4));

        // Yard ↔ Yard
        Quantity q5 = new Quantity(2.0, LengthUnit.YARDS);
        Quantity q6 = new Quantity(2.0, LengthUnit.YARDS);

        System.out.println("2 yards vs 2 yards: " + q5.equals(q6));

        // CM ↔ CM
        Quantity q7 = new Quantity(2.0, LengthUnit.CENTIMETERS);
        Quantity q8 = new Quantity(2.0, LengthUnit.CENTIMETERS);

        System.out.println("2 cm vs 2 cm: " + q7.equals(q8));

        // CM ↔ Inches
        Quantity q9 = new Quantity(1.0, LengthUnit.CENTIMETERS);
        Quantity q10 = new Quantity(0.393701, LengthUnit.INCHES);

        System.out.println("1 cm vs 0.393701 inches: " + q9.equals(q10));

        // Non-equal case
        Quantity q11 = new Quantity(1.0, LengthUnit.CENTIMETERS);
        Quantity q12 = new Quantity(1.0, LengthUnit.FEET);

        System.out.println("1 cm vs 1 foot: " + q11.equals(q12));

        // Transitive check
        Quantity a = new Quantity(1.0, LengthUnit.YARDS);
        Quantity b = new Quantity(3.0, LengthUnit.FEET);
        Quantity c = new Quantity(36.0, LengthUnit.INCHES);

        System.out.println("Transitive (yard=feet & feet=inches): "
                + (a.equals(b) && b.equals(c) && a.equals(c)));

        // Null & reference checks
        System.out.println("Null check: " + a.equals(null));
        System.out.println("Same reference: " + a.equals(a));
    }
}