package io.github.tkaczenko.patterns.task1.interpeter;

import io.github.tkaczenko.patterns.task1.interpeter.expression.CostOfTransportation;
import io.github.tkaczenko.patterns.task1.interpeter.expression.PassengerCostOfTransportation;
import io.github.tkaczenko.patterns.task1.model.Vehicle;
import io.github.tkaczenko.patterns.task1.interpeter.expression.BaggageCostOfTransportation;

/**
 * Created by tkaczenko on 05.05.17.
 */
public class InterpreterClient {
    private InterpeterContext interpeterContext;

    public InterpreterClient(InterpeterContext interpeterContext) {
        this.interpeterContext = interpeterContext;
    }

    public double interpreter(String str, Vehicle vehicle, double distance) {
        CostOfTransportation costOfTransportation;
        if (str.contains("passenger")) {
            costOfTransportation = new PassengerCostOfTransportation(
                    vehicle, Integer.parseInt(str.substring(0, str.indexOf(" ")))
            );
        } else if (str.contains("baggage")) {
            costOfTransportation = new BaggageCostOfTransportation(
                    vehicle, Double.parseDouble(str.substring(0, str.indexOf(" ")))
            );
        } else {
            throw new UnsupportedOperationException("This operation is not supported");
        }
        return costOfTransportation.interpreter(interpeterContext, distance);
    }
}
