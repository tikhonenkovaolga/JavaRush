package com.javarush.task.task29.task2909.car;

import java.util.Date;

public class Cabriolet extends Car {
    public Cabriolet(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }

    @Override
    public int fill(double numberOfLiters) {
        return super.fill(numberOfLiters);
    }

    @Override
    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        return super.getTripConsumption(date, length, SummerStart, SummerEnd);
    }

    @Override
    public int getNumberOfPassengersCanBeTransferred() {
        return super.getNumberOfPassengersCanBeTransferred();
    }

    @Override
    public boolean isDriverAvailable() {
        return super.isDriverAvailable();
    }

    @Override
    public void setDriverAvailable(boolean driverAvailable) {
        super.setDriverAvailable(driverAvailable);
    }

    @Override
    public void startMoving() {
        super.startMoving();
    }

    @Override
    public void fastenPassengersBelts() {
        super.fastenPassengersBelts();
    }

    @Override
    public void fastenDriverBelt() {
        super.fastenDriverBelt();
    }

    @Override
    public int getMaxSpeed() {
        return super.getMaxSpeed();
    }
}
