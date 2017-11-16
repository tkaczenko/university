package io.github.tkaczenko.patterns.task1.model;

import io.github.tkaczenko.patterns.task1.bridge.VehicleBridge;

/**
 * Created by tkaczenko on 04.05.17.
 */
public class Auto extends Vehicle {
    private double costOfPetrol;
    private double numOfLitres;

    public Auto(double speed, double costOfPetrol, double numOfLitres, double capacity, VehicleBridge vehicleBridge) {
        super(speed, capacity, vehicleBridge);
        this.costOfPetrol = costOfPetrol;
        this.numOfLitres = numOfLitres;
        this.costOfOneKm = numOfLitres * costOfPetrol;
    }


    @Override
    public double getTravelTime(double distance) {
        return vehicleBridge.getTravelTime(distance, speed);
    }

    @Override
    public double getCostOfTransportation(double distance) {
        return vehicleBridge.getCostOfTransportation(distance, costOfOneKm);
    }

    public double getCostOfPetrol() {
        return costOfPetrol;
    }

    public void setCostOfPetrol(double costOfPetrol) {
        this.costOfPetrol = costOfPetrol;
    }

    public double getNumOfLitres() {
        return numOfLitres;
    }

    public void setNumOfLitres(double numOfLitres) {
        this.numOfLitres = numOfLitres;
    }
}
