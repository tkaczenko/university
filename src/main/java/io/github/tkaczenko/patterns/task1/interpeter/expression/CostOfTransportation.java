package io.github.tkaczenko.patterns.task1.interpeter.expression;

import io.github.tkaczenko.patterns.task1.interpeter.InterpeterContext;
import io.github.tkaczenko.patterns.task1.model.Vehicle;

/**
 * Created by tkaczenko on 05.05.17.
 */
public abstract class CostOfTransportation {
    protected Vehicle vehicle;

    protected CostOfTransportation(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract double interpreter(InterpeterContext interpeterContext, double distance);
}
