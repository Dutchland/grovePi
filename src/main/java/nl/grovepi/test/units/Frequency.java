package nl.grovepi.test.units;

import nl.grovepi.test.CustomAsserts;

public class Frequency {
    private final double hertz;

    private Frequency(double hertz) {
        CustomAsserts.assertBiggerThanZero(hertz, () -> this.handleInValidFrequency("Frequency has to be positive"));
        this.hertz = hertz;
    }

    public static Frequency fromHertz(int hertz) {
        return new Frequency(hertz);
    }

    public double getHertz() {
        return this.hertz;
    }
    
    private void handleInValidFrequency(String message) {
        throw new RuntimeException(message);
    }
}
