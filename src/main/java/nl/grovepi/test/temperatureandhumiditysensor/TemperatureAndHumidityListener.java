package nl.grovepi.test.temperatureandhumiditysensor;

import nl.grovepi.test.temperatureandhumiditysensor.TemperatureAndHumidityDataPoint;

public interface TemperatureAndHumidityListener {
    void onDataReceived(TemperatureAndHumidityDataPoint dataPoint);
}
