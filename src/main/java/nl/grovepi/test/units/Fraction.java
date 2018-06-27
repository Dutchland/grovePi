package nl.grovepi.test.units;

import nl.grovepi.test.CustomAsserts;

import static nl.grovepi.test.CustomAsserts.assertNotBiggerThan;
import static nl.grovepi.test.CustomAsserts.assertZeroOrBigger;

public class Fraction {
    private final double fraction;

    private Fraction(double fraction) {
        this.fraction = fraction;
    }

    public static Fraction ofPercentage(double percentage) {
        assertNotBiggerThan(percentage, 100d, () -> errorHandler("A fractional percentage cannot be over 100%: " + percentage));
        assertZeroOrBigger(percentage, () -> errorHandler("Fractional percentage cannot be negative"));

        return new Fraction(percentage / 100d);
    }

    public static Fraction ofFraction(double fraction) {
        assertNotBiggerThan(fraction, 1d, () -> errorHandler("A fraction cannot be bigger than 1.0d: " + fraction));
        assertZeroOrBigger(fraction, () -> errorHandler("Fraction cannot be negative"));

        return new Fraction(fraction);
    }

    private static void errorHandler(String message) {
        throw new InvalidFractionException(message);
    }

    public static class InvalidFractionException extends ValidationException {
        private InvalidFractionException(String message) {
            super(message);
        }
    }
}
