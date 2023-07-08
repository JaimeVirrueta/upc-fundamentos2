package Views;

import Controllers.ClientController;
import Models.Bay;
import Models.Client;
import Models.Model;

public class ClientView extends View{

    private ClientController controller;

    public void setController(ClientController controller){
        this.controller = controller;
    }
    public String getNombre() {
        return sc.getString("Ingrese el nombre: ");
    }

    public String getCell_phone() {
        String cell = sc.getString("Ingrese su número de celular : ");

        return this.validateCell_phone(cell);
    }

    private String validateCell_phone(String cell) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getByCell_phone(cell);
            if (indice != -1) {
                sc.printAlerta("El número '" + cell + "' ya está registrado");

                cell = this.getCell_phone();
            } else {
                salir = true;
            }
        }

        return cell;
    }

    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion 1 : Gestión de Clientes");
            sc.printSubtitulo("1. Crear cliente");
            sc.printSubtitulo("2. Listar cliente");
            sc.printSubtitulo("3. Actualizar cliente");
            sc.printSubtitulo("4. Eliminar cliente");
            sc.printSubtitulo("5. Salir");
            int opcion = sc.getInt("   Ingrese una opción: ");

            switch (opcion) {
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
                case 5:
                    salir = true;
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }
    @Override
    public void create(){
        sc.printTitulo("Opción 1.1: Creación de Clientes");

        Client client = this.controller.save(
                this.getNombre(),
                this.getCell_phone()
        );

        sc.printCorrecto("Cliente agregada correctamente");
        sc.print(this.toString(client));
    }
    @Override
    public void index() {
        sc.printTitulo("Opción 1.2: Listado de Clientes");
        if (this.controller.getClients().size() == 0) {
            sc.printAlerta("No hay clientes en la lista");
        } else {
            for (Client client : this.controller.getClients()) {
                sc.print(this.toString(client));
            }
        }
    }

    @Override
    public void update() {
        sc.printTitulo("Opción 1.3: Gestión de Cliente");
        int codigo = sc.getInt("Ingrese el código del cliente a actualizar: ");

        int index = this.controller.getById(codigo);
        if (index != -1) {
            Client client = this.controller.getByIndex(index);

            sc.printCorrecto("Cliente encontrado:");
            sc.print(this.toString(client));

            client.setNombre(this.getNombre());
            client.setCell_phone(this.getCell_phone());

            sc.printCorrecto("Cliente actualizado correctamente");
        } else {
            sc.printAlerta("Cliente no encontrado");
        }
    }
    @Override
    public void delete() {
        sc.printTitulo("Opción 1.4: Eliminación de Cliente");
        int code = sc.getInt("Ingrese el código del cliente a eliminar: ");

        try {
            Client client = this.controller.get(code);

            sc.printCorrecto("Cliente encontrado: ");
            sc.print(this.toString(client));

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Cliente no encontrado");
        }
    }
    @Override
    public String toString(Model model) {
        Client client = (Client) model;

        return super.sc.getVerde("Código: ")  + client.getCodigo()
                + super.sc.getVerde(", Nombre: ") + client.getNombre()
                + super.sc.getVerde(", Celular: ") + client.getCell_phone();
    }


}