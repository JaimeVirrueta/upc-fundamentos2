package Render;

import Controllers.OrderController;
import Models.Order;
import java.util.ArrayList;

public class OrderRender extends Render {

    private OrderController controller;
    private int sizeName = 0;
    private int sizeCustomer = 0;
    private int sizeVehicle = 0;
    private int sizeBay = 0;
    private int sizeProfessional = 0;
    private int sizeType = 0;

    public OrderRender(OrderController controller) {
        this.controller = controller;
    }

    public void printTable(ArrayList<Order> orders) {
        sizeName = Math.max(this.sizeName(orders), 6);
        sizeCustomer = Math.max(this.sizeCustomer(orders), 6);
        sizeVehicle = Math.max(this.sizeVehicle(orders), 8);
        sizeBay = Math.max(this.sizeBay(orders), 5);
        sizeProfessional = Math.max(sizeProfessional(orders), 11);
        sizeType = Math.max(this.sizeType(orders), 4);

        this.line();
        this.header();
        this.line();

        for (Order order : orders) {
            this.content(order);
        }

        this.line();
    }

    public void printTable(Order order) {
        sizeName = Math.max(order.getName().length(), 6);
        sizeCustomer = Math.max(this.controller.getCustomer(order).length(), 6);
        sizeVehicle = Math.max(this.controller.getVehicle(order).length(), 8);
        sizeBay = Math.max(this.controller.getBay(order).length(), 5);
        sizeProfessional = Math.max(this.controller.getProfessional(order).length(), 11);
        sizeType = Math.max(order.getOrderType().length(), 4);


        this.line();
        this.header();
        this.line();
        this.content(order);
        this.line();
    }

    private void content(Order order) {
        String vehicle = this.controller.getVehicle(order);
        String customer = this.controller.getCustomer(order);
        String bay = this.controller.getBay(order);
        String professional = this.controller.getProfessional(order);

        sc.print(String.format(format()
                , order.getIdText()
                , order.getName()
                , order.getOrderType()
                , vehicle
                , customer
                , bay
                , professional
                , order.getStartDate()
                , order.getEndDate()
        ));
    }

    protected void line() {
        sc.print("+" + this.dashes(8)
                + "+" + this.dashes(sizeName + 2)
                + "+" + this.dashes(sizeType + 2)
                + "+" + this.dashes(sizeVehicle + 2)
                + "+" + this.dashes(sizeCustomer + 2)
                + "+" + this.dashes(sizeBay + 2)
                + "+" + this.dashes(sizeProfessional + 2)
                + "+" + this.dashes(16 + 2)
                + "+" + this.dashes(16 + 2)
                + "+");
    }

    private void header() {
        sc.print(String.format(this.format()
                , "Código"
                , "Nombre"
                , "Tipo"
                , "Vehículo"
                , "Cliente"
                , "Bahia"
                , "Profesional"
                , "Inicio"
                , "Fin"
        ));
    }

    private String format() {
        return "| %-6s | "
            + "%-" + sizeName + "s | "
            + "%-" + sizeType + "s | "
            + "%-" + sizeVehicle + "s | "
            + "%-" + sizeCustomer + "s | "
            + "%-" + sizeBay + "s | "
            + "%-" + sizeProfessional + "s | "
            + "%-" + 16 + "s | "
            + "%-" + 16 + "s | "
            ;
    }

    private int sizeName(ArrayList<Order> orders) {
        int size = 0;
        int tmp;
        for (Order order : orders) {
            tmp = order.getName().length();
            if (tmp > size) {
                size = tmp;
            }
        }

        return size;
    }

    private int sizeCustomer(ArrayList<Order> orders) {
        int size = 0;
        int tmp;
        for (Order order : orders) {
            tmp = this.controller.getCustomer(order).length();
            if (tmp > size) {
                size = tmp;
            }
        }

        return size;
    }

    private int sizeType(ArrayList<Order> orders) {
        int size = 0;
        int tmp;
        for (Order order : orders) {
            tmp = order.getOrderType().length();
            if (tmp > size) {
                size = tmp;
            }
        }

        return size;
    }

    private int sizeVehicle(ArrayList<Order> orders) {
        int size = 0;
        int tmp;
        for (Order order : orders) {
            tmp = this.controller.getVehicle(order).length();
            if (tmp > size) {
                size = tmp;
            }
        }

        return size;
    }

    private int sizeBay(ArrayList<Order> orders) {
        int size = 0;
        int tmp;
        for (Order order : orders) {
            tmp = this.controller.getBay(order).length();
            if (tmp > size) {
                size = tmp;
            }
        }

        return size;
    }

    private int sizeProfessional(ArrayList<Order> orders) {
        int size = 0;
        int tmp;
        for (Order order : orders) {
            tmp = this.controller.getProfessional(order).length();
            if (tmp > size) {
                size = tmp;
            }
        }

        return size;
    }
}
