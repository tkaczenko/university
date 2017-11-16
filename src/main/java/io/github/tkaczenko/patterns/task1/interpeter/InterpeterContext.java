package io.github.tkaczenko.patterns.task1.interpeter;

import io.github.tkaczenko.patterns.task1.model.Vehicle;

/**
 * Created by tkaczenko on 05.05.17.
 */
public class InterpeterContext {
    public double getCostOfTransportationForPassenger(int numOfPassengers, double distance, Vehicle vehicle) {
        return numOfPassengers * vehicle.getCostOfTransportation(distance);
    }

    public double getCostOfTransportationForBaggage(double weightOfBaggage,  double distance, Vehicle vehicle) {
        return weightOfBaggage / vehicle.getCapacity() * vehicle.getCostOfTransportation(distance);
    }
}
