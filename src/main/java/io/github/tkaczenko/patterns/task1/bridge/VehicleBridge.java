package io.github.tkaczenko.patterns.task1.bridge;

/**
 * Created by tkaczenko on 05.05.17.
 */
public interface VehicleBridge {
    double getTravelTime(double distance, double speed);

    double getCostOfTransportation(double distance, double costOfOneKm);
}
