package nl.grovepi.test;

import nl.grovepi.test.units.Frequency;
import org.iot.raspberry.grovepi.devices.GroveLed;

import java.io.IOException;

public class GroveLedWrapper implements Led {
    private final GroveLed groveLed;

    public GroveLedWrapper(GroveLed groveLed) {
        this.groveLed = groveLed;
    }

    @Override
    public void turnOn() {
        try {
            this.groveLed.set(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void turnOff() {
        try {
            this.groveLed.set(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void blink(Frequency blinkingFrequency) {

    }
}
