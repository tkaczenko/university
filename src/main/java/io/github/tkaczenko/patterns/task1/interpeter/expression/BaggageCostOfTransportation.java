package io.github.tkaczenko.patterns.task1.interpeter.expression;

import io.github.tkaczenko.patterns.task1.interpeter.InterpeterContext;
import io.github.tkaczenko.patterns.task1.model.Vehicle;

/**
 * Created by tkaczenko on 05.05.17.
 */
public class BaggageCostOfTransportation extends CostOfTransportation {
    private double weightOfBaggage;

    public BaggageCostOfTransportation(Vehicle vehicle, double weightOfBaggage) {
        super(vehicle);
        this.weightOfBaggage = weightOfBaggage;
    }

    @Override
    public double interpreter(InterpeterContext interpeterContext, double distance) {
        return interpeterContext.getCostOfTransportationForBaggage(this.weightOfBaggage, distance, this.vehicle);
    }
}
