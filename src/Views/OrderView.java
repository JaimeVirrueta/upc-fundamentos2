package Views;

import Controllers.OrderController;
import Models.Model;
import Models.Order;
import Models.Product;

public class OrderView extends View{
    private OrderController controller;

    private int menuOption = 1;
    private int subMenuOption = 0;

    public void setController(OrderController controller) {
        this.controller = controller;
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
            sc.printSubtitulo("3. Gestionar órden de trabajo");
            sc.printSubtitulo("4. Cerrar órden de trabajo");
            sc.printSubtitulo("5. Actualizar órden de trabajo");
            sc.printSubtitulo("6. Eliminar órden de trabajo");
            sc.printSubtitulo("7. Salir");
            this.subMenuOption = sc.getInt("   Ingrese una opción: ");

            switch ( this.subMenuOption) {
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
                    this.closeOrder();
                    break;
                case 5:
                    this.update();
                    break;
                case 6:
                    this.delete();
                    break;
                case 7:
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
        if (this.controller.getOrders().size() == 0) {
            sc.printAlerta("No hay Ordenes de trabajo registradas.");
        } else {
            for (Order order : this.controller.getOrders()) {
                sc.print(this.toString(order));
            }
        }
    }

    public void show() {

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

        return super.sc.getVerde("Código: ")  + order.getCodigo()
            + super.sc.getVerde(", Orden: ") + order.getNombre()
            + super.sc.getVerde(", Tipo: ") + order.getOrderType()
            + super.sc.getVerde(", Vehiculo: ") + this.controller.getVehicle(order)
            + super.sc.getVerde("\n           Kilometraje: ") + order.getMileage()
            + super.sc.getVerde(", Cliente: ") + this.controller.getCustomer(order)
            + super.sc.getVerde(", Fecha de Inicio: ") + order.getStartDate()
            + super.sc.getVerde(", Fecha de Fin: ") + order.getEndDate()
            + super.sc.getVerde("\n           Profesional: ") + this.controller.getProfessional(order)
            + super.sc.getVerde(", Bahia: ") + this.controller.getBay(order)
        ;
    }

}
