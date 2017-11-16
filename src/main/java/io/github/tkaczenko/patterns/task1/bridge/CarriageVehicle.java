package io.github.tkaczenko.patterns.task1.bridge;

/**
 * Created by tkaczenko on 05.05.17.
 */
public class CarriageVehicle implements VehicleBridge {
    @Override
    public double getTravelTime(double distance, double speed) {
        return distance / speed;
    }

    @Override
    public double getCostOfTransportation(double distance, double costOfOneKm) {
        return distance * costOfOneKm;
    }
}
