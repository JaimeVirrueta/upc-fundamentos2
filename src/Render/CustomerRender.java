package Render;

import Models.Customer;
import java.util.ArrayList;

public class CustomerRender extends Render {

    private int sizeName = 0;
    private int sizePhone = 0;

    public void printTable(ArrayList<Customer> customers) {
        sizeName = Math.max(this.sizeName(customers), 6);
        sizePhone = Math.max(this.sizePhone(customers), 8);

        this.line();
        this.header();
        this.line();

        for (Customer customer : customers) {
            this.content(customer);
        }

        this.line();
    }

    public void printTable(Customer customer) {
        sizeName = Math.max(customer.getName().length(), 6);
        sizePhone = Math.max(customer.getPhone().length(), 8);

        this.line();
        this.header();
        this.line();
        this.content(customer);
        this.line();
    }

    private void content(Customer customer) {
        String id = customer.getIdText();
        String name = customer.getName();
        String phone = customer.getPhone();
        sc.print(String.format(format(), id, name, phone));
    }

    private void line() {
        sc.print("+" + this.dashes(8) + "+" + this.dashes(sizeName + 2) + "+" + this.dashes(sizePhone + 2) + "+");
    }

    private void header() {
        sc.print(String.format(format(), "Código", "Nombre", "Teléfono"));
    }

    private String format() {
        return "| %-6s | %-" + sizeName + "s | %-" + sizePhone + "s |";
    }

    private int sizeName(ArrayList<Customer> customers) {
        int size = 0;
        for (Customer customer : customers) {
            if (customer.getName().length() > size) {
                size = customer.getName().length();
            }
        }

        return size;
    }

    private int sizePhone(ArrayList<Customer> customers) {
        int size = 0;
        for (Customer customer : customers) {
            if (customer.getPhone().length() > size) {
                size = customer.getPhone().length();
            }
        }

        return size;
    }
}
