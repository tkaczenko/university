package io.github.tkaczenko.patterns.task1.model;

import io.github.tkaczenko.patterns.task1.bridge.VehicleBridge;

/**
 * Created by tkaczenko on 04.05.17.
 */
public abstract class Vehicle {
    protected double speed;
    protected double costOfOneKm;
    protected double capacity;
    protected VehicleBridge vehicleBridge;

    protected Vehicle(double speed, double capacity, VehicleBridge vehicleBridge) {
        this.speed = speed;
        this.vehicleBridge = vehicleBridge;
        this.capacity = capacity;
    }

    public abstract double getTravelTime(double distance);

    public abstract double getCostOfTransportation(double distance);

    public double getSpeed() {
        return speed;
    }

    public double getCostOfOneKm() {
        return costOfOneKm;
    }

    public double getCapacity() {
        return capacity;
    }

    public VehicleBridge getVehicleBridge() {
        return vehicleBridge;
    }
}
