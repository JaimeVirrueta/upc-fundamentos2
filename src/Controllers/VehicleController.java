package Controllers;

import Models.Vehicle;
import Models.Model;
import Views.VehicleView;
import java.util.ArrayList;

public class VehicleController extends Controller{
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private VehicleView view;

    public VehicleController(VehicleView view) {
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }

    @Override
    public int modelSize(){
        return this.getVehicles().size();
    }

    public Vehicle save(String licensePlate, String brand, String modelCar) {
        Vehicle vehicle = new Vehicle(this.modelSize(), licensePlate, brand, modelCar);
        this.getVehicles().add(vehicle);

        return vehicle;
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.getVehicles().get(i);
            if (model.getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Vehicle getByIndex(int index) {

        return this.getVehicles().get(index);
    }

    public int getByLicensePlate(String licensePlate) {
        for (int i = 0; i < this.modelSize(); i++) {
            Vehicle vehicle = this.getByIndex(i);
            if (vehicle.getLicensePlate().equalsIgnoreCase(licensePlate)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void delete(int id) {
        int index = this.getById(id);
        this.getVehicles().remove(index);
    }

    public void initData() {
        this.save("ABC-123", "Toyota","Auris");
        this.save("CBA-231", "Hyundai","Bayon");
        this.save("BCA-312", "Kia","Carens");
    }

    public int getNameSize() {
        int size = 0;
        for (Vehicle vehicle : this.getVehicles()) {
            if (vehicle.getName().length() > size) {
                size = vehicle.getName().length();
            }
        }

        return size;
    }
}
