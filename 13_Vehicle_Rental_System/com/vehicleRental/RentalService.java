package com.vehicleRental;

import java.util.ArrayList;
import java.util.List;

public class RentalService {

    private static List<Vehicle> vehicles =
            new ArrayList<>();

    private static double totalRevenue = 0;

    static {

        vehicles.add(
                new Vehicle(
                        "TN01",
                        "Car",
                        1500));

        vehicles.add(
                new Vehicle(
                        "TN02",
                        "Bike",
                        500));

        vehicles.add(
                new Vehicle(
                        "TN03",
                        "SUV",
                        2500));
    }

    public static void startApplication() {

        while (true) {

            IO.println("\n===== VEHICLE RENTAL SYSTEM =====");
            IO.println("1. View Vehicles");
            IO.println("2. Rent Vehicle");
            IO.println("3. Return Vehicle");
            IO.println("4. Search Vehicle");
            IO.println("5. View Available Vehicles");
            IO.println("6. View Revenue");
            IO.println("7. Exit");

            int choice =
                    Integer.parseInt(
                            IO.readln("Enter Choice: "));

            switch (choice) {

            case 1:
                viewVehicles();
                break;

            case 2:
                rentVehicle();
                break;

            case 3:
                returnVehicle();
                break;

            case 4:
                searchVehicle();
                break;

            case 5:
                availableVehicles();
                break;

            case 6:
                IO.println(
                        "Revenue : "
                                + totalRevenue);
                break;

            case 7:
                return;

            default:
                IO.println("Invalid Choice!");
            }
        }
    }

    private static void viewVehicles() {

        for (Vehicle vehicle : vehicles) {

            IO.println(vehicle);
        }
    }

    private static void rentVehicle() {

        String vehicleNo =
                IO.readln("Vehicle Number: ");

        int days =
                Integer.parseInt(
                        IO.readln("Days: "));

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleNo()
                    .equalsIgnoreCase(vehicleNo)) {

                if (vehicle.isRented()) {

                    IO.println(
                            "Already Rented!");
                    return;
                }

                vehicle.setRented(true);

                double bill =
                        vehicle.getRentPerDay()
                                * days;

                totalRevenue += bill;

                IO.println(
                        "Vehicle Rented!");
                IO.println(
                        "Bill : " + bill);

                return;
            }
        }

        IO.println("Vehicle Not Found!");
    }

    private static void returnVehicle() {

        String vehicleNo =
                IO.readln("Vehicle Number: ");

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleNo()
                    .equalsIgnoreCase(vehicleNo)) {

                vehicle.setRented(false);

                IO.println(
                        "Vehicle Returned!");
                return;
            }
        }

        IO.println("Vehicle Not Found!");
    }

    private static void searchVehicle() {

        String vehicleNo =
                IO.readln("Vehicle Number: ");

        for (Vehicle vehicle : vehicles) {

            if (vehicle.getVehicleNo()
                    .equalsIgnoreCase(vehicleNo)) {

                IO.println(vehicle);
                return;
            }
        }

        IO.println("Vehicle Not Found!");
    }

    private static void availableVehicles() {

        for (Vehicle vehicle : vehicles) {

            if (!vehicle.isRented()) {

                IO.println(vehicle);
            }
        }
    }
}