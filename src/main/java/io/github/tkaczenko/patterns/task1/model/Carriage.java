package io.github.tkaczenko.patterns.task1.model;

import io.github.tkaczenko.patterns.task1.bridge.VehicleBridge;

/**
 * Created by tkaczenko on 04.05.17.
 */
public class Carriage extends Vehicle {
    public Carriage(double speed, double capacity, VehicleBridge vehicleBridge) {
        super(speed, capacity, vehicleBridge);
    }

    @Override
    public double getTravelTime(double distance) {
        return vehicleBridge.getTravelTime(distance, speed);
    }

    @Override
    public double getCostOfTransportation(double distance) {
        return vehicleBridge.getCostOfTransportation(distance, speed);
    }
}
