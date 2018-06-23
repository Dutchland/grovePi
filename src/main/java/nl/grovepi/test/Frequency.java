package nl.grovepi.test;

public class Frequency {
    private final int perSecond;

    private Frequency(int perSecond) {
        CustomAsserts.assertBiggerThanZero(perSecond, () -> "Frequency has to be positive");
        this.perSecond = perSecond;
    }

    public Frequency perSecond(int perSecond) {
        return new Frequency(perSecond);
    }
}
