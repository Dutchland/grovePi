package nl.grovepi.test.temperatureandhumiditysensor;

public interface TemperatureAndHumiditySensor {
    void start();
    void addListener(TemperatureAndHumidityListener listener);
}
