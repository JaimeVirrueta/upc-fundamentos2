package Controllers;

import Models.Car;
import Models.Model;
import Models.Order;
import Models.Product;
import Services.Screen;
import Services.Utils;
import Views.OrderView;

import java.util.ArrayList;

public class OrderController extends Controller {
    Screen sc = new Screen();
    private ClientController customerController;
    private ProfessionalController professionalController;
    private BayController bayController;
    private CarController vehicleController;
    private ProductController productController;
    private ArrayList<Order> orders = new ArrayList<>();
    private Order model;
    private OrderView view;

    public OrderController(Order order, OrderView orderView) {
        this.model = order;
        this.view = orderView;
        this.view.setController(this);
    }

    public void setControllers(
            ClientController customerController
            , ProfessionalController professionalController
            , BayController bayController
            , CarController carController,
              ProductController productController
    ) {
        this.customerController = customerController;
        this.professionalController = professionalController;
        this.bayController = bayController;
        this.vehicleController = carController;
        this.productController = productController;
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public int modelSize(){
        return this.getOrders().size();
    }

    public Order save(Order order) {
        order.setCodigo(this.modelSize());
        order.setStartDate(Utils.getCurrentDateTime());
        this.orders.add(order);

        return order;
    }

    @Override
    public Order get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.orders.get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Order getByIndex(int index) {
        return this.orders.get(index);
    }

    @Override
    public void delete(int id) {
        int index = this.getById(id);
        this.orders.remove(index);
    }

    public int getVehicle(String licensePlate) {
        return this.vehicleController.getByLicense_plate(licensePlate);
    }

    public int getVehicle(int index) {
        return this.vehicleController.getByIndex(index).getCodigo();
    }

    public String getVehicle(Order order) {
        Car car = this.vehicleController.get(order.getVehicleId());

        return car.getNombre();
    }

    public int getCustomer(int customerId) {
        int index = this.customerController.getById(customerId);

        return this.customerController.getByIndex(index).getCodigo();
    }

    public String getCustomer(Order order) {
        return this.customerController.get(order.getCustomerId()).getNombre();
    }

    public int getProfessional(int professionalId) {
        int index = this.professionalController.getById(professionalId);

        return this.professionalController.getByIndex(index).getCodigo();
    }

    public String getProfessional(Order order) {
        return this.professionalController.get(order.getProfessionalId()).getNombre();
    }

    public int getBay(int bay) {
        int index = this.bayController.getById(bay);

        return this.bayController.getByIndex(index).getCodigo();
    }

    public String getBay(Order order) {
        return this.bayController.get(order.getBayId()).getNombre();
    }

    public void initData() {
        this.save(new Order(this.modelSize(), "Preventivo 10000km", 1, 1, 1, 1, "Preventivo", "2023-01-15 13:25", "2023-01-15 18:25", 9850));
        this.save(new Order(this.modelSize(), "Correctivo Supension", 2, 3, 2, 2, "Correctivo", "2023-04-15 09:25", "2023-04-15 15:32", 67836));
    }
}
