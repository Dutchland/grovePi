package nl.grovepi.test;

public class CustomAsserts {
    public static void assertBiggerThanZero(double value, AssertionFailedHandler handler) {
        if (value <= 0d) {
            handler.handleAssertionFailed();
        }
    }

    public static void assertZeroOrBigger(double value, AssertionFailedHandler handler) {
        if (value < 0d) {
            handler.handleAssertionFailed();
        }
    }

    public static void assertNotBiggerThan(double value, double comparedTo, AssertionFailedHandler handler) {
        if (value > comparedTo) {
            handler.handleAssertionFailed();
        }
    }

    @FunctionalInterface
    public interface AssertionFailedHandler {
        void handleAssertionFailed();
    }
}
