package nl.grovepi.test.temperatureandhumiditysensor;

import nl.grovepi.test.units.Frequency;
import org.iot.raspberry.grovepi.devices.GroveTemperatureAndHumiditySensor;
import org.iot.raspberry.grovepi.devices.GroveTemperatureAndHumidityValue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class GroveTemperatureAndHumiditySensorWrapper implements TemperatureAndHumiditySensor{
    private static final int MILLISECONDS_PER_SECOND = 1_000;

    private Collection<TemperatureAndHumidityListener> listeners = new ArrayList<>();
    private final long delayInMilliseconds;
    private final SensorPollTask task;
    private final Timer timer;

//    public GroveTemperatureAndHumiditySensorWrapper(GroveTemperatureAndHumiditySensor sensor, Frequency pollFrequency, TemperatureAndHumidityListener... listeners) {
//        this(sensor, pollFrequency);
//        this.listeners.addAll(listeners);
//    }

    public GroveTemperatureAndHumiditySensorWrapper(GroveTemperatureAndHumiditySensor sensor, Frequency pollFrequency) {
        this.task = new SensorPollTask(sensor);
        this.delayInMilliseconds = calculateDelayInMilliseconds(pollFrequency);
        this.timer = new Timer();
    }


    @Override
    public void start() {
        this.timer.scheduleAtFixedRate(this.task, 0, this.delayInMilliseconds);
    }

    @Override
    public void addListener(TemperatureAndHumidityListener listener) {
        Collection<TemperatureAndHumidityListener> newListeners = new ArrayList<>();
        newListeners.addAll(this.listeners);
        newListeners.add(listener);

        this.listeners = newListeners;
    }

    private static long calculateDelayInMilliseconds(Frequency frequency) {
        return (int) Math.ceil((double)MILLISECONDS_PER_SECOND / frequency.getHertz());
    }

    private void onDataPointReceived(GroveTemperatureAndHumidityValue originalDataPoint, LocalDateTime timeStamp) {
        TemperatureAndHumidityDataPoint dataPoint = new TemperatureAndHumidityDataPoint(originalDataPoint.getTemperature(), originalDataPoint.getHumidity(), timeStamp);

        this.listeners
                .forEach(l -> l.onDataReceived(dataPoint));
    }

    private class SensorPollTask extends TimerTask {
        private final GroveTemperatureAndHumiditySensor sensor;

        private SensorPollTask(GroveTemperatureAndHumiditySensor sensor) {
            this.sensor = sensor;
        }

        @Override
        public void run() {
            try {
                onDataPointReceived(this.sensor.get(), LocalDateTime.now());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
