package Models;

public class Vehicle extends Model{

    private String licensePlate;

    private String brand;

    private String modelCar;

    public Vehicle(int id, String licensePlate, String brand, String modelCar) {
        super.setId(id);
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.modelCar = modelCar;
    }

    @Override
    public String getName() {
        return this.getBrand() + " - " + this.getModelCar() + " (" + this.getLicensePlate() + ")";
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
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
