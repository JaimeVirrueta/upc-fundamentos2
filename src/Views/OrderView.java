package Views;

import Controllers.OrderController;
import Controllers.ProductController;
import Controllers.ProfessionalController;
import Models.Model;
import Models.Order;
import Models.Product;
import Models.Professional;
import Render.OrderRender;

import java.util.ArrayList;

public class OrderView extends View{

    private OrderController controller;

    private OrderRender render;

    private ProductController productController;

    private ProfessionalController professionalController;

    public void setController(OrderController controller) {
        this.controller = controller;
        this.render = new OrderRender(controller);
        this.menuOption = 1;
    }

    @Override
    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            input.printTitulo("Opcion " + this.menuOption + " : Gestión de órdenes de Trabajo");
            input.printSubtitulo("1. Crear");
            input.printSubtitulo("2. Listar");
            input.printSubtitulo("3. Visualizar");
            input.printSubtitulo("4. Gestionar productos de OT");
            input.printSubtitulo("5. Gestionar profesionales de OT");
            input.printSubtitulo("6. Actualizar");
            input.printSubtitulo("7. Finalizar OT");
            input.printSubtitulo("8. Eliminar");
            input.printSubtitulo("0. Salir");
            this.subMenuOption = input.getInt("   Ingrese una opción: ");

            switch (this.subMenuOption) {
                case 1:
                    this.create();
                    break;
                case 2:
                    this.index();
                    break;
                case 3:
                    this.show();
                    break;
                case 4:
                    this.manageProducts();
                    break;
                case 5:
                    this.manageProfessionals();
                    break;
                case 6:
                    this.update();
                    break;
                case 7:
                    this.closeOrder();
                    break;
                case 8:
                    this.delete();
                    break;
                case 0:
                    salir = true;
                    break;
                default:
                    input.printAlerta("Opción inválida");
            }
        }
    }

    @Override
    public void create(){
        input.printTitulo(this.subTitle("Creación de Orden de Trabajo"));

        Order order = new Order();
        order.setName(this.inputName());
        order.setOrderType(this.inputOrderType());
        order.setBayId(this.inputBay());
        order.setVehicleId(this.inputVehicle());
        order.setMileage(this.inputMileage());
        order.setCustomerId(this.inputCustomer());
        order.setProfessionalId(this.inputProfessional());
        order = this.controller.save(order);

        order.addProfessional((Professional) this.professionalController.get(order.getProfessionalId()));

        input.printCorrecto("OT creada correctamente");
        render.printTable(order);
    }

    @Override
    public void index() {
        input.printTitulo(this.subTitle("Listado de Órdenes de Trabajo"));
        if (this.controller.getOrders().isEmpty()) {
            input.printAlerta("No hay Órdenes de trabajo registradas");
        } else {
            render.printTable(this.controller.getOrders());
        }
    }

    public void show() {
        input.printTitulo(this.subTitle("Visualización de Órden de Trabajo"));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Order order = this.controller.getByIndex(index);
            render.printTableDetail(order);
        } else {
            input.printAlerta("OT no encontrada");
        }
    }

    @Override
    public void update() {
        input.printTitulo(this.subTitle("Actualización de Orden de Trabajo"));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Order order = this.controller.getByIndex(index);

            input.printCorrecto("OT encontrada:");
            render.printTable(order);

            order.setBayId(this.inputBay());
            order.setProfessionalId(this.inputProfessional());

            input.printCorrecto("Resultado:");
            render.printTable(order);

            input.printCorrecto("OT actualizada correctamente");
        } else {
            input.printAlerta("OT no encontrada");
        }
    }

    public void closeOrder() {
        input.printTitulo(this.subTitle("Finalizar Orden de Trabajo"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Order order = (Order) this.controller.get(code);

            input.printCorrecto("OT encontrada y cerrada:");
            this.controller.closeOrder(order);
            render.printTable(order);

        } catch (Exception e) {
            input.printAlerta("Producto no encontrado");
        }
    }

    @Override
    public void delete() {
        input.printTitulo(this.subTitle("Eliminación de Orden de Trabajo"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Order order = (Order) this.controller.get(code);

            input.printCorrecto("Orden de trabajo encontrada:");
            render.printTable(order);

            String confirmDelete = "";
            while (!confirmDelete.equalsIgnoreCase("S") && !confirmDelete.equalsIgnoreCase("N")) {
                confirmDelete = input.getString("¿Estás seguro de querer eliminar esta orden? (S/N): ");

                if (confirmDelete.equalsIgnoreCase("S")) {
                    this.controller.delete(code);
                    input.printCorrecto("Orden eliminada correctamente");
                } else if (confirmDelete.equalsIgnoreCase("N")) {
                    input.printAlerta("Eliminación cancelada");
                } else {
                    input.printAlerta("Por favor solo ingresar S/N");
                }
            }

        } catch (Exception e) {
            input.printAlerta("Orden de trabajo no encontrada");
        }
    }

    /**
     * Entrada de datos
     */

    public String inputName() {
        return input.getString("Ingrese el nombre: ");
    }

    public int inputVehicle() {
        String vehicle = this.input.getString("Ingrese la placa del vehçiulo: ");

        return this.validateVehicle(vehicle);
    }

    public int inputCustomer() {
        int customerId = this.input.getInt("Ingrese el id del cliente: ");

        return this.validateCustomer(customerId);
    }

    public int inputMileage() {
        return this.input.getInt("Ingrese el kilometraje recorrido: ");
    }

    public String inputOrderType() {
        String orderType = this.input.getString("Ingrese el tipo de orden " + input.getCian("(p = Preventivo | c = Correctivo)") + ": ");

        return this.validateOrderType(orderType);
    }

    public int inputProfessional() {
        int professionalId = this.input.getInt("Ingrese el ID del profesional a cargo: ");

        return this.validateProfessional(professionalId);
    }

    public int inputBay() {
        int bay = input.getInt("Ingrese el ID de la bahia asignada: ");

        return this.validateBay(bay);
    }
    
    /**
     * Validaciones
     */

    private int validateVehicle(String licensePlate) {
        int vehicleId = this.controller.getVehicle(licensePlate);

        if (vehicleId == -1) {
            String txt = "La placa del vehículo no está registrada";
            txt += input.getCian("\n   Ingrese una placa registrada o aborte el proceso y registre el vehiculo previamente");
            input.printAlerta(txt);
            this.inputVehicle();
        }

        return this.controller.getVehicle(vehicleId);
    }

    private int validateCustomer(int customerId) {
        customerId = this.controller.getCustomer(customerId);

        if (customerId == -1) {
            input.printAlerta("El ID del cliente es incorrecto");
            this.inputCustomer();
        }

        return customerId;
    }

    private String validateOrderType(String orderType) {
        if (orderType.equalsIgnoreCase("p")) {
            orderType = "Preventivo";
        } else if (orderType.equalsIgnoreCase("c")) {
            orderType = "Correctivo";
        } else {
            input.printAlerta("El Tipo de orden ingresado es incorrecto");
            this.inputOrderType();
        }

        return orderType;
    }

    private int validateProfessional(int professionalId) {
        professionalId = this.controller.getProfessional(professionalId);

        if (professionalId == -1) {
            input.printAlerta("El ID del profesional asignado es incorrecto");
            this.inputProfessional();
        }

        return professionalId;
    }

    private int validateBay(int bay) {
        bay = this.controller.getBay(bay);

        if (bay == -1) {
            input.printAlerta("El ID de la bahía es incorrecto");
            this.inputBay();
        }

        return bay;
    }


    /**
     * =============================================================================================
     * Gestión de Profesionales asignados a la ORden de trabajo
     */

    public void setProfessionalController(ProfessionalController professionalController) {
        this.professionalController = professionalController;
    }

    public void manageProfessionals() {
        int codigo = input.getInt("Ingrese el código de la orden de trabajo: ");
        int index = controller.getById(codigo);

        if (index != -1) {
            Order order = controller.getByIndex(index);
            render.printTableDetail(order);
            boolean salir = false;
            while (!salir) {
                input.printTitulo("Gestión de Profesionales de la Orden de Trabajo");
                input.printSubtitulo("1. Agregar profesional");
                input.printSubtitulo("2. Eliminar profesional");
                input.printSubtitulo("3. Volver");
                int opcion = input.getInt("Ingrese una opción: ");

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
                        input.printAlerta("Opción inválida");
                }
            }
        } else {
            input.printAlerta("Orden de trabajo no encontrada");
        }
    }

    public void addProfessional(Order order) {
        int codigo = input.getInt("Ingrese el código del profesional a agregar: ");
        int index = professionalController.getById(codigo);

        if (index != -1) {
            Professional professional = professionalController.getByIndex(index);

            order.addProfessional(professional);

            input.printCorrecto("profesional agregado a la orden correctamente");
            render.printTableDetail(order);
        } else {
            input.printAlerta("profesional no encontrado");
        }
    }

    public void removeProfessional(Order order) {
        int codigo = input.getInt("Ingrese el código del profesional a eliminar: ");
        int index = getProfessionalIndex(order, codigo);

        if (index != -1) {
            Professional professional = order.getProfessionals().get(index);

            order.removeProfessional(professional);

            input.printCorrecto("profesional eliminado de la orden correctamente");
            render.printTableDetail(order);
        } else {
            input.printAlerta("profesional no encontrado");
        }
    }

    private int getProfessionalIndex(Order order, int codigo) {
        for (int i = 0; i < order.getProfessionals().size(); i++) {
            Professional professional = order.getProfessionals().get(i);
            if (professional.getId() == codigo) {
                return i;
            }
        }

        return -1;
    }


    /**
     * =============================================================================================
     * Gestión de Productos usados en la Orden de trabajo
     */

    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    public void manageProducts() {
        int codigo = input.getInt("Ingrese el código de la orden de trabajo: ");
        int index = controller.getById(codigo);

        if (index != -1) {
            Order order = controller.getByIndex(index);
            render.printTableDetail(order);
            boolean salir = false;
            while (!salir) {
                input.printTitulo("Gestión de Productos de la Orden de Trabajo");
                input.printSubtitulo("1. Agregar producto");
                input.printSubtitulo("2. Eliminar producto");
                input.printSubtitulo("3. Volver");
                int opcion = input.getInt("Ingrese una opción: ");

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
                        input.printAlerta("Opción inválida");
                }
            }
        } else {
            input.printAlerta("Orden de trabajo no encontrada");
        }
    }

    // Metodos add, remove, manage, getIndex de Productos
    public void addProduct(Order order) {
        int codigo = input.getInt("Ingrese el código del producto a agregar: ");
        int index = productController.getById(codigo);

        if (index != -1) {
            Product product = productController.getByIndex(index);
            order.addProduct(product);
            input.printCorrecto("Producto agregado a la orden correctamente");
            render.printTableDetail(order);
        } else {
            input.printAlerta("Producto no encontrado");
        }
    }

    public void removeProduct(Order order) {
        int codigo = input.getInt("Ingrese el código del producto a eliminar: ");
        int index = getProductIndex(order, codigo);

        if (index != -1) {
            Product product = order.getProducts().get(index);

            order.removeProduct(product);

            input.printCorrecto("Producto eliminado de la orden correctamente");
            render.printTableDetail(order);
        } else {
            input.printAlerta("Producto no encontrado");
        }
    }

    private int getProductIndex(Order order, int codigo) {
        for (int i = 0; i < order.getProducts().size(); i++) {
            Product product = order.getProducts().get(i);
            if (product.getId() == codigo) {
                return i;
            }
        }

        return -1;
    }


}
