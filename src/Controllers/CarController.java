package Controllers;

import Models.Car;
import Models.Model;
import Views.CarView;

import java.util.ArrayList;

public class CarController extends Controller{

    private ArrayList<Car> cars = new ArrayList<>();
    private Car model;
    private CarView view;

    public CarController(Car model, CarView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Car> getCars() {
        return this.cars;
    }

    public Car save(String license_plate, String brand,String car_model) {
        Car car = new Car(this.cars.size(), license_plate, brand,car_model);
        this.cars.add(car);

        return car;
    }

    @Override
    public Car get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.cars.size(); i++) {
            Model model = this.cars.get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Car getByIndex(int index) {
        return this.cars.get(index);
    }

    public int getByLicense_plate(String license_plate) {
        for (int i = 0; i < this.cars.size(); i++) {
            Car car = (Car) this.getByIndex(i);
            if (car.getLicensePlate().equalsIgnoreCase(license_plate)) {
                return i;
            }
        }

        return -1;
    }
    public void delete(int id) {
        int index = this.getById(id);
        this.cars.remove(index);
    }

    public void initData() {
        this.save("ABC-123", "Toyota","Auris");
        this.save("CBA-231", "Hyundai","Bayon");
        this.save("BCA-312", "Kia","Carens");
    }
}
