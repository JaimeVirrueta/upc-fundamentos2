package Views;

import Controllers.CustomerController;
import Models.Customer;

public class CustomerView extends View{

    private CustomerController controller;

    public void setController(CustomerController controller){
        this.controller = controller;
        this.menuOption = 5;
    }

    @Override
    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            input.printTitulo("Opcion "+ this.menuOption +" : Gestión de Clientes");
            input.printSubtitulo("1. Crear");
            input.printSubtitulo("2. Listar");
            input.printSubtitulo("3. Actualizar");
            input.printSubtitulo("4. Eliminar");
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
                    this.update();
                    break;
                case 4:
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
        input.printTitulo(this.subTitle("Creación de Cliente."));

        Customer customer = this.controller.save(
                this.inputName(),
                this.inputPhone()
        );

        input.printCorrecto("Cliente creado correctamente");
        tbl.printTable(customer);
    }
    @Override
    public void index() {
        input.printTitulo("Opción 1.2: Listado de Clientes");
        if (this.controller.getCustomers().size() == 0) {
            input.printAlerta("No hay clientes registrados");
        } else {
            tbl.printTable(this.controller.getCustomers(), this.controller);
        }
    }

    @Override
    public void update() {
        input.printTitulo(this.subTitle("Gestión del Cliente."));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Customer customer = this.controller.getByIndex(index);

            input.printCorrecto("Cliente encontrado:");
            tbl.printTable(customer);

            customer.setName(this.inputName());
            customer.setPhone(this.inputPhone());

            input.printCorrecto("Cliente actualizado correctamente");
        } else {
            input.printAlerta("Cliente no encontrado");
        }
    }
    @Override
    public void delete() {
        input.printTitulo(this.subTitle("Eliminación de Cliente"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Customer customer = (Customer) this.controller.get(code);

            input.printCorrecto("Cliente encontrado: ");
            tbl.printTable(customer);

            this.controller.delete(code);

        } catch (Exception e) {
            input.printAlerta("Cliente no encontrado");
        }
    }

    /**
     * Entrada de datos
     */

    public String inputName() {
        return input.getString("Ingrese el nombre: ");
    }

    public String inputPhone() {
        return this.validatePhone(input.getString("Ingrese su número de celular : "));
    }

    /**
     * Validaciones
     */

    private String validatePhone(String phone) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getByCell_phone(phone);
            if (indice != -1) {
                input.printAlerta("El número '" + phone + "' ya está registrado");
                phone = this.inputPhone();
            } else {
                salir = true;
            }
        }

        return phone;
    }

}
