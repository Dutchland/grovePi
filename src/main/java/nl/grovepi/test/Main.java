package nl.grovepi.test;

import nl.grovepi.test.button.Button;
import nl.grovepi.test.button.GroveButton;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.devices.GroveLed;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello GrovePi");
        GrovePi grovePi = new GrovePi4J();

        Led led = new GroveLedAdapter(new GroveLed(grovePi, 1));
        Button button = new GroveButton(grovePi, 2);

        button.setListener(isPressed -> handleLed(isPressed, led));
    }

    private static void handleLed(boolean isPressed, Led led) {
        if (isPressed) {
            led.turnOn();
        } else {
            led.turnOff();
        }
    }
}
