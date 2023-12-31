package Views;

import Controllers.BayController;
import Models.Bay;
import Render.BayRender;

public class BayView extends View{

    private BayRender render = new BayRender();

    private BayController controller;

    public void setController(BayController controller) {
        this.controller = controller;
        this.menuOption = 4;
    }

    @Override
    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            input.printTitulo("Opcion "+ this.menuOption +" : Gestión de Bahias");
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
        input.printTitulo(this.subTitle("Creación de Bahia"));

        Bay bay = this.controller.save(
                this.inputName(),
                this.getLocal()
        );

        input.printCorrecto("Bahia creada correctamente");
        render.printTable(bay);
    }

    @Override
    public void index() {
        input.printTitulo(this.subTitle("Listado de Bahias"));
        if (this.controller.getBays().size() == 0) {
            input.printAlerta("No hay bahias en la lista");
        } else {
            render.printTable(this.controller.getBays());
        }
    }

    @Override
    public void update() {
        input.printTitulo(this.subTitle("Gestión de Bahia"));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Bay bay = this.controller.getByIndex(index);

            input.printCorrecto("Bahia encontrado:");
            render.printTable(bay);

            bay.setName(this.inputName());
            bay.setLocal(this.getLocal());

            input.printCorrecto("Resultado:");
            render.printTable(bay);

            input.printCorrecto("Bahia actualizado correctamente");
        } else {
            input.printAlerta("Bahia no encontrada");
        }
    }

    @Override
    public void delete() {
        input.printTitulo(this.subTitle("Eliminación de Bahia"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Bay bay = (Bay) this.controller.get(code);

            input.printCorrecto("Bahia encontrada: ");
            render.printTable(bay);

            String confirmDelete = "";
            while (!confirmDelete.equalsIgnoreCase("S") && !confirmDelete.equalsIgnoreCase("N")) {
                confirmDelete = input.getString("¿Estás seguro de querer eliminar esta bahia? (S/N): ");

                if (confirmDelete.equalsIgnoreCase("S")) {
                    this.controller.delete(code);
                    input.printCorrecto("Bahia eliminada correctamente");
                } else if (confirmDelete.equalsIgnoreCase("N")) {
                    input.printAlerta("Eliminación cancelada");
                } else {
                    input.printAlerta("Por favor solo ingresar S/N");
                }
            }
        } catch (Exception e) {
            input.printAlerta("Bahia no encontrada");
        }
    }

    /**
     * Entrada de datos
     */

    public String inputName() {
        return this.validateName(input.getString("Ingrese el nombre: "));
    }

    public String getLocal() {
        return this.input.getString("Ingrese el Local: ");
    }

    /**
     * Validaciones
     */

    private String validateName(String name) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getByName(name);
            if (indice != -1) {
                input.printAlerta("El Nombre '" + name + "' ya está registrado");
                name = this.inputName();
            } else {
                salir = true;
            }
        }

        return name;
    }
}
