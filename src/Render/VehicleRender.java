package Render;

import Models.Vehicle;
import java.util.ArrayList;

public class VehicleRender extends Render {

    private int sizePlate = 0;
    private int sizeBrand = 0;
    private int sizeModel = 0;

    //@Override
    public void printTable(ArrayList<Vehicle> vehicles) {
        sizePlate = Math.max(this.sizePlate(vehicles), 6);
        sizeBrand = Math.max(this.sizeBrand(vehicles), 5);
        sizeModel = Math.max(this.sizeModel(vehicles), 6);

        this.line();
        this.header();
        this.line();

        for (Vehicle vehicle : vehicles) {
            this.content(vehicle);
        }

        this.line();
    }

    //@Override
    public void printTable(Vehicle vehicle) {
        sizePlate = Math.max(vehicle.getLicensePlate().length(), 6);
        sizeBrand = Math.max(vehicle.getBrand().length(), 5);
        sizeModel = Math.max(vehicle.getModelCar().length(), 6);

        this.line();
        this.header();
        this.line();
        this.content(vehicle);
        this.line();
    }

    private void content(Vehicle vehicle) {
        String id = vehicle.getIdText();
        String plate = vehicle.getLicensePlate();
        String brand = vehicle.getBrand();
        String model = vehicle.getModelCar();
        sc.print(String.format(format(), id, plate, brand, model));
    }

    private void line() {
        sc.print("+" + dashes(8) + "+" + dashes(sizePlate + 2) + "+" + dashes(sizeBrand + 2) + "+" + dashes(sizeModel + 2) + "+");
    }

    private void header() {
        sc.print(String.format(format(), "Código", "Placa", "Marca", "Modelo"));
    }

    private String format() {
        return "| %-6s | %-" + sizePlate + "s | %-" + sizeBrand + "s | %-" + sizeModel + "s |";
    }

    private int sizePlate(ArrayList<Vehicle> vehicles) {
        int size = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().length() > size) {
                size = vehicle.getLicensePlate().length();
            }
        }

        return size;
    }

    private int sizeBrand(ArrayList<Vehicle> vehicles) {
        int size = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getBrand().length() > size) {
                size = vehicle.getBrand().length();
            }
        }

        return size;
    }

    private int sizeModel(ArrayList<Vehicle> vehicles) {
        int size = 0;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModelCar().length() > size) {
                size = vehicle.getModelCar().length();
            }
        }

        return size;
    }

}
