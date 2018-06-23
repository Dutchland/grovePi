package nl.grovepi.test;

public interface Led {
    void turnOn();
    void turnOff();
    void blink(Frequency blinkingFrequency);
}
