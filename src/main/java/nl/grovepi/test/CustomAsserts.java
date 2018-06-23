package nl.grovepi.test;

import java.util.function.Supplier;

public class CustomAsserts {
    public static void assertBiggerThanZero(int value, Supplier<String> messageSupplier) {
        if (value <= 0) {
            throw new RuntimeException(messageSupplier.get());
        }
    }
}
