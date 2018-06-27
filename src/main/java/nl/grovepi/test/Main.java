package nl.grovepi.test;

import nl.grovepi.test.button.Button;
import nl.grovepi.test.button.GroveButton;
import nl.grovepi.test.temperatureandhumiditysensor.GroveTemperatureAndHumiditySensorWrapper;
import nl.grovepi.test.temperatureandhumiditysensor.TemperatureAndHumidityDataPoint;
import nl.grovepi.test.temperatureandhumiditysensor.TemperatureAndHumiditySensor;
import nl.grovepi.test.units.Frequency;
import org.iot.raspberry.grovepi.GrovePi;
import org.iot.raspberry.grovepi.devices.GroveLed;
import org.iot.raspberry.grovepi.devices.GroveTemperatureAndHumiditySensor;
import org.iot.raspberry.grovepi.pi4j.GrovePi4J;

import java.io.IOException;

public class Main {
    private static final int TEMPERATURE_AND_HUMIDITY_SENSOR_PIN_NUMBER = 3;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello GrovePi");
        GrovePi grovePi = new GrovePi4J();

        GroveTemperatureAndHumiditySensor groveSensor = new GroveTemperatureAndHumiditySensor(grovePi, TEMPERATURE_AND_HUMIDITY_SENSOR_PIN_NUMBER, GroveTemperatureAndHumiditySensor.Type.DHT11);
        TemperatureAndHumiditySensor temperatureAndHumiditySensor =
                new GroveTemperatureAndHumiditySensorWrapper(groveSensor, Frequency.fromHertz(1));

        temperatureAndHumiditySensor.addListener(Main::printDataPoint);
        temperatureAndHumiditySensor.start();
//
//
//        Led led = new GroveLedWrapper(new GroveLed(grovePi, 1));
//        Button button = new GroveButton(grovePi, 2);
//
//        button.setListener(isPressed -> switchLedStatus(isPressed, led));
    }

    private static void printDataPoint(TemperatureAndHumidityDataPoint dataPoint) {
        System.out.println(dataPoint);
    }

    private static void switchLedStatus(boolean turnOn, Led led) {
        if (turnOn) {
            led.turnOn();
        } else {
            led.turnOff();
        }
    }
}
