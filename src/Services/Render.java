package Services;

import Controllers.*;
import Models.*;

import java.util.ArrayList;

public class Render {

    public Input sc = new Input();

    public void printTable(ArrayList<Order> orders, OrderController controller) {

    }

    public void printTable(Order order) {

    }

    public void printTable(ArrayList<Product> products, ProductController controller) {

    }

    public void printTable(Product product) {

    }

    public void printTable(ArrayList<Professional> professionals, ProfessionalController controller) {
        int size = controller.getNameSize();
        size = Math.max(size, 6);

        String line = "+" + this.dashes(8) + "+" + this.dashes(size + 2) + "+" + this.dashes(15) + "+";

        sc.print(line);
        sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", "Código", "Nombre", "Profesión"));
        sc.print(line);

        for (Professional professional : professionals) {
            int id = professional.getId();
            String name = professional.getName();
            String profession = professional.getProfession();

            sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", id + "", name, profession));
        }

        sc.print(line);
    }

    public void printTable(Professional professional) {
        int size = professional.getName().length();
        size = Math.max(size, 6);

        String line = "+" + this.dashes(8) + "+" + this.dashes(size + 2) + "+" + this.dashes(15) + "+";

        sc.print(line);
        sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", "Código", "Nombre", "Profesión"));
        sc.print(line);
        int id = professional.getId();
        String name = professional.getName();
        String profession = professional.getProfession();
        sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", id + "", name, profession));
        sc.print(line);
    }

    public void printTable(ArrayList<Bay> bays, BayController controller) {

    }

    public void printTable(Bay bay) {

    }

    public void printTable(ArrayList<Customer> customers, CustomerController controller) {

    }

    public void printTable(Customer customer) {

    }

    public void printTable(ArrayList<Vehicle> vehicles, VehicleController controller) {

    }

    public void printTable(Vehicle vehicle) {

    }


    private String fill(String texto, int longitudTotal, String relleno) {
        return String.format("%" + longitudTotal + "s", texto).replace(' ', relleno.charAt(0));
    }

    private String dashes(int quantity) {
        return this.fill("-", quantity, "-");
    }
}
