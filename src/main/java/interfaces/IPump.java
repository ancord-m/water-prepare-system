package interfaces;

public interface IPump {
    void act(IValve valve);
    boolean getState();
}
