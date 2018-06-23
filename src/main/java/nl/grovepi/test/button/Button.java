package nl.grovepi.test.button;

public interface Button {
    void setListener(ButtonStatusChangesListener listener);
    boolean isPressed();
}
