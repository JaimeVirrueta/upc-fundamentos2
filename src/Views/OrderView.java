package Views;

import Controllers.OrderController;
import Controllers.ProductController;
import Controllers.ProfessionalController;
import Models.Model;
import Models.Order;
import Models.Product;
import Models.Professional;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class OrderView extends View{
    private OrderController controller;
    private ProductController productController;
    private ProfessionalController professionalController;
    private int menuOption = 1;
    private int subMenuOption = 0;

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }
    public void setController(OrderController controller) {
        this.controller = controller;
    }
    public void setProfessionalController(ProfessionalController professionalController) {
        this.professionalController = professionalController;
    }

    public String getName() {
        return sc.getString("Ingrese el nombre: ");
    }

    public int getVehicle() {
        String licensePlate =  this.sc.getString("Ingrese la placa del vehçiulo: ");

        return this.validateVehicle(licensePlate);
    }

    private int validateVehicle(String licensePlate) {
        int vehicleId = this.controller.getVehicle(licensePlate);

        if (vehicleId == -1) {
            String txt = "La placa del vehículo no está registrada";
            txt += sc.getCian("\n   Ingrese una placa registrada o aborte el proceso y registre el vehiculo previamente");
            sc.printAlerta(txt);
            this.getVehicle();
        }

        return this.controller.getVehicle(vehicleId);
    }

    public int getCustomer() {
        int customerId = this.sc.getInt("Ingrese el id del cliente: ");

        return this.validateCustomer(customerId);
    }

    private int validateCustomer(int customerId) {
        customerId = this.controller.getCustomer(customerId);

        if (customerId == -1) {
            sc.printAlerta("El ID del cliente es incorrecto.");
            this.getCustomer();
        }

        return customerId;
    }

    public int getMileage() {
        return this.sc.getInt("Ingrese el kilometraje recorrido: ");
    }

    public String getOrderType() {
        String orderType = this.sc.getString("Ingrese el tipo de orden " + sc.getCian("(p = Preventivo | c = Correctivo)") + ": ");

        return this.validateOrderType(orderType);
    }

    private String validateOrderType(String orderType) {
        if (orderType.equalsIgnoreCase("p")) {
            orderType = "Preventivo";
        } else if (orderType.equalsIgnoreCase("c")) {
            orderType = "Correctivo";
        } else {
            sc.printAlerta("El Tipo de orden ingresado es incorrecto");
            this.getOrderType();
        }

        return orderType;
    }

    public int getProfessional() {
        int professionalId = this.sc.getInt("Ingrese el ID del profesional a cargo: ");

        return this.validateProfessional(professionalId);
    }

    private int validateProfessional(int professionalId) {
        professionalId = this.controller.getProfessional(professionalId);

        if (professionalId == -1) {
            sc.printAlerta("El ID del profesional asignado es incorrecto.");
            this.getProfessional();
        }

        return professionalId;
    }

    public int getBay() {
        int bay = sc.getInt("Ingrese el ID de la bahia asignada: ");

        return this.validateBay(bay);
    }

    private int validateBay(int bay) {
        bay = this.controller.getBay(bay);

        if (bay == -1) {
            sc.printAlerta("El ID de la bahía es incorrecto");
            this.getBay();
        }

        return bay;
    }

    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion " + this.menuOption + " : Gestión de órdenes de Trabajo.");
            sc.printSubtitulo("1. Crear órden de trabajo");
            sc.printSubtitulo("2. Listar órden de trabajos");
            sc.printSubtitulo("3. Gestionar productos");
            sc.printSubtitulo("4. Gestionar profesionales");
            sc.printSubtitulo("5. Actualizar órden de trabajo");
            sc.printSubtitulo("6. Finalizar órden de trabajo");
            sc.printSubtitulo("7. Eliminar órden de trabajo");
            sc.printSubtitulo("8. Salir");
            this.subMenuOption = sc.getInt("   Ingrese una opción: ");

            switch ( this.subMenuOption) {
                case 1:
                    this.create();
                    break;
                case 2:
                    this.index();
                    break;
                case 3:
                    this.manageProducts();
                    break;
                case 4:
                    this.manageProfessionals();
                    break;
                case 5:
                    this.update();
                    break;
                case 6:
                    this.closeOrder();
                    break;
                case 7:
                    this.delete();
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

    private String subTitle(String txt) {
        return "Opción " + this.menuOption + "." + this.subMenuOption + ": " + txt;
    }

    @Override
    public void create(){
        sc.printTitulo(this.subTitle("Creación de Orden de Trabajo."));

        Order order = new Order();
        order.setNombre(this.getName());
        order.setOrderType(this.getOrderType());
        order.setBayId(this.getBay());
        order.setVehicleId(this.getVehicle());
        order.setMileage(this.getMileage());
        order.setCustomerId(this.getCustomer());
        order.setProfessionalId(this.getProfessional());
        order = this.controller.save(order);

        sc.printCorrecto("OT creada correctamente.");
        sc.print(this.toString(order));
    }

    @Override
    public void index() {
        sc.printTitulo(this.subTitle("Listado de Órdenes de Trabajo"));
        if (this.controller.getOrders().isEmpty()) {
            sc.printAlerta("No hay Órdenes de trabajo registradas.");
        } else {
            for (Order order : this.controller.getOrders()) {
                sc.print(this.toString(order));
            }
        }
    }

    // Metodos add, remove, manage, getIndex de Productos
    public void addProduct(Order order) {
        int codigo = sc.getInt("Ingrese el código del producto a agregar: ");
        int index = productController.getById(codigo);

        if (index != -1) {
            Product product = productController.getByIndex(index);

            order.addProduct(product);

            sc.printCorrecto("Producto agregado a la orden correctamente.");
        } else {
            sc.printAlerta("Producto no encontrado.");
        }
    }

    public void removeProduct(Order order) {
        int codigo = sc.getInt("Ingrese el código del producto a eliminar: ");
        int index = getProductIndex(order, codigo);

        if (index != -1) {
            Product product = order.getProducts().get(index);

            order.removeProduct(product);

            sc.printCorrecto("Producto eliminado de la orden correctamente.");
        } else {
            sc.printAlerta("Producto no encontrado.");
        }
    }

    public void manageProducts() {
        int codigo = sc.getInt("Ingrese el código de la orden de trabajo: ");
        int index = controller.getById(codigo);

        if (index != -1) {
            Order order = controller.getByIndex(index);
            boolean salir = false;
            while (!salir) {
                sc.printTitulo("Gestión de Productos de la Orden de Trabajo");
                sc.printSubtitulo("1. Agregar producto");
                sc.printSubtitulo("2. Eliminar producto");
                sc.printSubtitulo("3. Volver");
                int opcion = sc.getInt("Ingrese una opción: ");

                switch (opcion) {
                    case 1:
                        addProduct(order);
                        break;
                    case 2:
                        removeProduct(order);
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        sc.printAlerta("Opción inválida");
                }
            }
        } else {
            sc.printAlerta("Orden de trabajo no encontrada.");
        }
    }

    private int getProductIndex(Order order, int codigo) {
        for (int i = 0; i < order.getProducts().size(); i++) {
            Product product = order.getProducts().get(i);
            if (product.getCodigo() == codigo) {
                return i;
            }
        }

        return -1;
    }

    // Metodos add, remove, manage, getIndex de Profesionales

    public void addProfessional(Order order) {
        int codigo = sc.getInt("Ingrese el código del profesional a agregar: ");
        int index = professionalController.getById(codigo);

        if (index != -1) {
            Professional professional = professionalController.getByIndex(index);

            order.addProfessional(professional);

            sc.printCorrecto("profesional agregado a la orden correctamente.");
        } else {
            sc.printAlerta("profesional no encontrado.");
        }
    }

    public void removeProfessional(Order order) {
        int codigo = sc.getInt("Ingrese el código del profesional a eliminar: ");
        int index = getProfessionalIndex(order, codigo);

        if (index != -1) {
            Professional professional = order.getProfessionals().get(index);

            order.removeProfessional(professional);

            sc.printCorrecto("profesional eliminado de la orden correctamente.");
        } else {
            sc.printAlerta("profesional no encontrado.");
        }
    }

    public void manageProfessionals() {
        int codigo = sc.getInt("Ingrese el código de la orden de trabajo: ");
        int index = controller.getById(codigo);

        if (index != -1) {
            Order order = controller.getByIndex(index);
            boolean salir = false;
            while (!salir) {
                sc.printTitulo("Gestión de Profesionales de la Orden de Trabajo");
                sc.printSubtitulo("1. Agregar profesional");
                sc.printSubtitulo("2. Eliminar profesional");
                sc.printSubtitulo("3. Volver");
                int opcion = sc.getInt("Ingrese una opción: ");

                switch (opcion) {
                    case 1:
                        addProfessional(order);
                        break;
                    case 2:
                        removeProfessional(order);
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        sc.printAlerta("Opción inválida");
                }
            }
        } else {
            sc.printAlerta("Orden de trabajo no encontrada.");
        }
    }

    private int getProfessionalIndex(Order order, int codigo) {
        for (int i = 0; i < order.getProfessionals().size(); i++) {
            Professional professional = order.getProfessionals().get(i);
            if (professional.getCodigo() == codigo) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void update() {
        sc.printTitulo(this.subTitle("Actualización de Orden de Trabajo."));
        int codigo = sc.getInt("Ingrese el código de la OT a actualizar: ");

        int index = this.controller.getById(codigo);
        if (index != -1) {
            Order order = this.controller.getByIndex(index);

            sc.printCorrecto("OT encontrada:");
            sc.print(this.toString(order));

            order.setBayId(this.getBay());
            order.setProfessionalId(this.getProfessional());

            sc.printCorrecto("OT actualizada correctamente.");
        } else {
            sc.printAlerta("OT no encontrada.");
        }
    }

    public void closeOrder() {
        sc.printTitulo(this.subTitle("Finalizar Orden de Trabajo"));
        int code = sc.getInt("Ingrese el código: ");

        try {
            Order order = this.controller.get(code);

            sc.printCorrecto("OT encontrada:");
            sc.print(this.toString(order));

            this.controller.closeOrder(order);

        } catch (Exception e) {
            sc.printAlerta("Producto no encontrado");
        }
    }

    @Override
    public void delete() {
        sc.printTitulo(this.subTitle("Eliminación de Orden de Trabajo"));
        int code = sc.getInt("Ingrese el código de la OT a eliminar: ");

        try {
            Order order = this.controller.get(code);

            sc.printCorrecto("OT encontrada:");
            sc.print(this.toString(order));

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Producto no encontrado");
        }
    }

    @Override
    public String toString(Model model) {
        Order order = (Order) model;

        StringBuilder sb = new StringBuilder();
        sb.append(super.sc.getVerde("Código: ")).append(order.getCodigo())
                .append(super.sc.getVerde(", Orden: ")).append(order.getNombre())
                .append(super.sc.getVerde(", Tipo: ")).append(order.getOrderType())
                .append(super.sc.getVerde(", Vehículo: ")).append(this.controller.getVehicle(order))
                .append(super.sc.getVerde("\n           Kilometraje: ")).append(order.getMileage())
                .append(super.sc.getVerde(", Cliente: ")).append(this.controller.getCustomer(order))
                .append(super.sc.getVerde(", Fecha de Inicio: ")).append(order.getStartDate())
                .append(super.sc.getVerde(", Fecha de Fin: ")).append(order.getEndDate())
                .append(super.sc.getVerde("\n           Profesional: ")).append(this.controller.getProfessional(order))
                .append(super.sc.getVerde(", Bahía: ")).append(this.controller.getBay(order));
        ArrayList<Professional> professionals = order.getProfessionals();
        if (!professionals.isEmpty()) {
            sb.append(super.sc.getVerde("\n           Profesionales:"));
            for (Professional professional : professionals) {
                sb.append("\n              ").append(super.sc.getVerde("Código: ")).append(professional.getCodigo())
                        .append(super.sc.getVerde(", Nombre: ")).append(professional.getNombre());
            }
        } else {
            sb.append("\n              No hay profesionales en esta orden.");
        }

        ArrayList<Product> products = order.getProducts();
        if (!products.isEmpty()) {
            sb.append(super.sc.getVerde("\n           Productos:"));
            for (Product product : products) {
                sb.append("\n              ").append(super.sc.getVerde("Código: ")).append(product.getCodigo())
                        .append(super.sc.getVerde(", Nombre: ")).append(product.getNombre());
            }
        } else {
            sb.append("\n              No hay productos en esta orden.");
        }
        return sb.toString();
    }
}
