package nl.grovepi.test;

import nl.grovepi.test.units.Frequency;

public interface Led {
    void turnOn();
    void turnOff();
    void blink(Frequency blinkingFrequency);
}
