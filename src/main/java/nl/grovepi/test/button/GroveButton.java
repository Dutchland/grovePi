package nl.grovepi.test.button;

import org.iot.raspberry.grovepi.GroveDigitalIn;
import org.iot.raspberry.grovepi.GrovePi;

import java.io.IOException;
import java.util.Optional;

public class GroveButton implements Button {

    private final GroveDigitalIn button;
    private Optional<ButtonStatusChangesListener> listener;

    public GroveButton(GrovePi grovePi, int portNumber, ButtonStatusChangesListener listener) {
        try {
            this.button = grovePi.getDigitalIn(portNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setListener(listener);
    }

    public GroveButton(GrovePi grovePi, int portNumber) {
        this(grovePi, portNumber, null);
    }

    public void setListener(ButtonStatusChangesListener listener) {
        this.listener = Optional.ofNullable(listener);
        this.listener.ifPresent(l -> this.button.setListener(this::onStatusChanged));
    }

    @Override
    public boolean isPressed() {
        try {
            return this.button.get();
        } catch (IOException|InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void onStatusChanged(boolean oldValue, boolean newValue) {
        this.listener
                .ifPresent(l -> l.onStatusChanged(newValue));
    }
}
