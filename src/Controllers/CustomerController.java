package Controllers;

import Models.Customer;
import Models.Model;
import Views.CustomerView;

import java.util.ArrayList;

public class CustomerController extends Controller{
    private ArrayList<Customer> customers = new ArrayList<>();
    private CustomerView view;

    public CustomerController(CustomerView view) {
        this.view = view;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Customer> getCustomers() {
        return this.customers;
    }

    @Override
    public int modelSize(){
        return this.getCustomers().size();
    }

    public Customer save(String name, String phone) {
        Customer customer = new Customer(this.getCustomers().size(), name, phone);
        this.getCustomers().add(customer);

        return customer;
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.getCustomers().get(i);
            if (model.getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Customer getByIndex(int index) {
        return this.getCustomers().get(index);
    }

    public int getByCell_phone(String cell) {
        for (int i = 0; i < this.modelSize(); i++) {
            Customer customer = this.getByIndex(i);
            if (customer.getPhone().equalsIgnoreCase(cell)) {
                return i;
            }
        }

        return -1;
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.customers.remove(index);
    }

    public void initData() {
        this.save("Fabricio", "999888777");
        this.save("Raul", "999888666");
        this.save("Roberto", "999888555");
    }

    public int getNameSize() {
        int size = 0;
        for (Customer customer : this.getCustomers()) {
            if (customer.getName().length() > size) {
                size = customer.getName().length();
            }
        }

        return size;
    }
}
