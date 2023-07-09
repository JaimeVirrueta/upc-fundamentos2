package Models;

public class Vehicle extends Model{

    private String license_plate;
    private String brand;
    private String modelCar;

    public Vehicle() {
    }

    public Vehicle(int id, String license_plate, String brand, String modelCar) {
        super.setCodigo(id);
        this.license_plate = license_plate;
        this.brand = brand;
        this.modelCar = modelCar;
    }

    @Override
    public String getNombre() {
        return this.getBrand() + " - " + this.getModelCar() + " (" + this.getLicensePlate() + ")";
    }

    public String getLicensePlate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }
}
