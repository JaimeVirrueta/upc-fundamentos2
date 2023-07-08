package Models;

public class Car extends Model{

    private String license_plate;
    private String brand;
    private String car_model;

    public Car() {
    }

    public Car(int id,String license_plate, String brand, String car_model) {
        super.setCodigo(id);
        this.license_plate = license_plate;
        this.brand = brand;
        this.car_model = car_model;
    }

    public String getLicense_plate() {
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

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }
}
