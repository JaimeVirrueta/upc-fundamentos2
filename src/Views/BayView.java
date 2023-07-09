package Views;

import Controllers.BayController;
import Models.Bay;
import Models.Model;
import Models.Product;

public class BayView extends View{
    private BayController controller;

    public void setController(BayController controller) {
        this.controller = controller;
    }

    public String getNombre() {
        String name = sc.getString("Ingrese el nombre: ");

        return this.validateName(name);
    }

    public String getLocal() {
        return this.sc.getString("Ingrese el Local: ");
    }

    private String validateName(String name) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getByName(name);
            if (indice != -1) {
                sc.printAlerta("El Nombre '" + name + "' ya está registrado");

                name = this.getNombre();
            } else {
                salir = true;
            }
        }

        return name;
    }

    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion 1 : Gestión de Bahias");
            sc.printSubtitulo("1. Crear bahia");
            sc.printSubtitulo("2. Listar bahias");
            sc.printSubtitulo("3. Actualizar bahia");
            sc.printSubtitulo("4. Eliminar bahia");
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
        sc.printTitulo("Opción 1.1: Creación de Bahia");

        Bay bay = this.controller.save(
            this.getNombre(),
            this.getLocal()
        );

        sc.printCorrecto("Bahia agregada correctamente");
        sc.print(this.toString(bay));
    }

    @Override
    public void index() {
        sc.printTitulo("Opción 1.2: Listado de Bahias");
        if (this.controller.getBays().size() == 0) {
            sc.printAlerta("No hay bahias en la lista");
        } else {
            for (Bay bay : this.controller.getBays()) {
                sc.print(this.toString(bay));
            }
        }
    }

    @Override
    public void update() {
        sc.printTitulo("Opción 1.3: Gestión de Bahia");
        int codigo = sc.getInt("Ingrese el código de la bahia a actualizar: ");

        int index = this.controller.getById(codigo);
        if (index != -1) {
            Bay bay = this.controller.getByIndex(index);

            sc.printCorrecto("Bahia encontrado:");
            sc.print(this.toString(bay));

            bay.setNombre(this.getNombre());
            bay.setLocal(this.getLocal());

            sc.printCorrecto("Bahia actualizado correctamente");
        } else {
            sc.printAlerta("Bahia no encontrado");
        }
    }

    @Override
    public void delete() {
        sc.printTitulo("Opción 1.4: Eliminación de Bahia");
        int code = sc.getInt("Ingrese el código de la bahia a eliminar: ");

        try {
            Bay bay = this.controller.get(code);

            sc.printCorrecto("Bahia encontrada: ");
            sc.print(this.toString(bay));

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Bahia no encontrado");
        }
    }


    public String toString(Model model) {
        Bay bay = (Bay) model;

        return super.sc.getVerde("Código: ")  + bay.getCodigo()
            + super.sc.getVerde(", Nombre: ") + bay.getNombre()
            + super.sc.getVerde(", Local: ") + bay.getLocal();
    }
}
