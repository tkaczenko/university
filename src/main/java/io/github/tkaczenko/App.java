package io.github.tkaczenko;

import io.github.tkaczenko.patterns.task1.bridge.AutoVehicle;
import io.github.tkaczenko.patterns.task1.bridge.BikeVehicle;
import io.github.tkaczenko.patterns.task1.bridge.CarriageVehicle;
import io.github.tkaczenko.patterns.task1.interpeter.InterpeterContext;
import io.github.tkaczenko.patterns.task1.interpeter.InterpreterClient;
import io.github.tkaczenko.patterns.task1.model.Auto;
import io.github.tkaczenko.patterns.task1.model.Bike;
import io.github.tkaczenko.patterns.task1.model.Carriage;
import io.github.tkaczenko.patterns.task1.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkaczenko on 04.05.17.
 */
public class App {
    private static List<Vehicle> vehicles = generateVehicle();

    public static void main(String[] args) {
        double distance = 10;
        System.out.println("Time of transportation:");
        vehicles.forEach(vehicle -> System.out.println(vehicle.getTravelTime(distance)));
        InterpreterClient interpreterClient = new InterpreterClient(new InterpeterContext());
        String commandPassenger = "10 passenger";
        String commandBaggage = "250 baggage";
        System.out.println("Cost of passenger transportation:");
        vehicles.forEach(vehicle ->
                System.out.println(interpreterClient.interpreter(commandPassenger, vehicle, distance)));
        System.out.println("Cost of baggage transportation:");
        vehicles.forEach(vehicle ->
                System.out.println(interpreterClient.interpreter(commandBaggage, vehicle, distance)));
    }

    private static List<Vehicle> generateVehicle() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Auto(30, 27, 3, 300, new AutoVehicle()));
        vehicles.add(new Bike(10, 100, new BikeVehicle()));
        vehicles.add(new Carriage(15, 200, new CarriageVehicle()));
        return vehicles;
    }
}
