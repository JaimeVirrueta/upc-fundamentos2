package Controllers;

import Models.Model;
import Models.Order;
import Services.Utils;
import Views.OrderView;
import java.util.ArrayList;

public class OrderController extends Controller {
    private CustomerController customerController;
    private ProfessionalController professionalController;
    private BayController bayController;
    private VehicleController vehicleController;
    private ProductController productController;
    private final ArrayList<Order> orders = new ArrayList<>();
    private final OrderView view;

    public OrderController(OrderView orderView) {
        this.view = orderView;
        this.view.setController(this);
    }

    public void setControllers(
            CustomerController customerController
            , ProfessionalController professionalController
            , BayController bayController
            , VehicleController vehicleController
            , ProductController productController
    ) {
        this.customerController = customerController;
        this.professionalController = professionalController;
        this.bayController = bayController;
        this.vehicleController = vehicleController;
        this.productController = productController;
    }

    @Override
    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    public int modelSize() {
        return this.getOrders().size();
    }

    public Order save(Order order) {
        order.setId(this.modelSize());
        order.setStartDate(Utils.getCurrentDateTime());
        this.getOrders().add(order);

        return order;
    }

    public Order closeOrder(Order order) {
        order.setEndDate(Utils.getCurrentDateTime());

        return order;
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.orders.get(i);
            if (model.getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Order getByIndex(int index) {
        return this.getOrders().get(index);
    }

    @Override
    public void delete(int id) {
        int index = this.getById(id);
        this.getOrders().remove(index);
    }

    public int getVehicle(String licensePlate) {
        return this.vehicleController.getByLicensePlate(licensePlate);
    }

    public int getVehicle(int index) {
        return this.vehicleController.getByIndex(index).getId();
    }

    public String getVehicle(Order order) {
        return this.vehicleController.get(order.getVehicleId()).getName();
    }

    public int getCustomer(int customerId) {
        int index = this.customerController.getById(customerId);

        return this.customerController.getByIndex(index).getId();
    }

    public String getCustomer(Order order) {
        return this.customerController.get(order.getCustomerId()).getName();
    }

    public int getProfessional(int professionalId) {
        int index = this.professionalController.getById(professionalId);

        return this.professionalController.getByIndex(index).getId();
    }

    public String getProfessional(Order order) {
        return this.professionalController.get(order.getProfessionalId()).getName();
    }

    public int getBay(int bay) {
        int index = this.bayController.getById(bay);

        return this.bayController.getByIndex(index).getId();
    }

    public String getBay(Order order) {
        return this.bayController.get(order.getBayId()).getName();
    }

    public void initData() {
        this.save(new Order(this.modelSize(), "Preventivo 10000km", 1, 1, 1, 1, "Preventivo", "2023-01-15 13:25", "2023-01-15 18:25", 9850));
        this.save(new Order(this.modelSize(), "Correctivo Supension", 2, 3, 2, 2, "Correctivo", "2023-04-15 09:25", "2023-04-15 15:32", 67836));
    }

    public int getNameSize() {
        int size = 0;
        for (Order order : this.getOrders()) {
            if (order.getName().length() > size) {
                size = order.getName().length();
            }
        }

        return size;
    }
}
