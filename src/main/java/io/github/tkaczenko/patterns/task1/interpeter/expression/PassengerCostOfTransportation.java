package io.github.tkaczenko.patterns.task1.interpeter.expression;

import io.github.tkaczenko.patterns.task1.interpeter.InterpeterContext;
import io.github.tkaczenko.patterns.task1.model.Vehicle;

/**
 * Created by tkaczenko on 05.05.17.
 */
public class PassengerCostOfTransportation extends CostOfTransportation {
    private int numOfPassanger;

    public PassengerCostOfTransportation(Vehicle vehicle, int numOfPassanger) {
        super(vehicle);
        this.numOfPassanger = numOfPassanger;
    }

    @Override
    public double interpreter(InterpeterContext interpeterContext, double distance) {
        return interpeterContext.getCostOfTransportationForPassenger(this.numOfPassanger, distance, this.vehicle);
    }
}
