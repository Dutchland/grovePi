package nl.grovepi.test.temperatureandhumiditysensor;

import java.time.LocalDateTime;

public class TemperatureAndHumidityDataPoint {
    private final double temperature;
    private final double humidity;
    private final LocalDateTime timeStamp;

    public TemperatureAndHumidityDataPoint(double temperature, double humidity, LocalDateTime timeStamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "TemperatureAndHumidityDataPoint{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
