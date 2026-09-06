package com.vehicleRental;

public class Vehicle {

    private String vehicleNo;
    private String model;
    private double rentPerDay;
    private boolean rented;

    public Vehicle(String vehicleNo,
                   String model,
                   double rentPerDay) {

        this.vehicleNo = vehicleNo;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.rented = false;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getModel() {
        return model;
    }

    public double getRentPerDay() {
        return rentPerDay;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    @Override
    public String toString() {

        return vehicleNo + " | "
                + model + " | "
                + rentPerDay + "/Day | "
                + (rented ? "Rented" : "Available");
    }
}