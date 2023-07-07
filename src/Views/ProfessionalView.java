package Views;

import Controllers.ProfessionalController;
import Models.Model;
import Models.Professional;

public class ProfessionalView extends View{
    private ProfessionalController controller;

    public void setController(ProfessionalController controller) {
        this.controller = controller;
    }

    public String getNombre() {
        return sc.getString("Ingrese el nombre: ");
    }

    public String getProfession() {
        String valor = sc.getString("Ingrese su especialidad (m) Mecánico  (e) Electricista : ");

        return this.validateProfession(valor);
    }

    private String validateProfession(String valor) {
        if (valor.equals("m")) {
            valor = "Mecánico";
        } else if (valor.equals("e")) {
            valor = "Electricista";
        } else {
            sc.printAlerta("El valor ingresado es incorrecto");
            valor = this.getProfession();
        }

        return valor;
    }

    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion 2: Gestión de Profesionales");
            sc.printSubtitulo("1. Crear profesional");
            sc.printSubtitulo("2. Listar profesionales");
            sc.printSubtitulo("3. Actualizar profesional");
            sc.printSubtitulo("4. Eliminar profesional");
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
        sc.printTitulo("Opción 1.1: Creación de Profesional");

        Professional professional = this.controller.save(getNombre(), getProfession());

        sc.printCorrecto("Profesional agregado correctamente");
        sc.print(this.toString(professional));
    }

    @Override
    public void index() {
        sc.printTitulo("Opción 1.2: Listado de Productos");
        if (this.controller.getProfessionals().size() == 0) {
            sc.printAlerta("No hay profesionales registrados");
        } else {
            for (Professional professsional : this.controller.getProfessionals()) {
                sc.print(this.toString(professsional));
            }
        }
    }

    @Override
    public void update() {
        sc.printTitulo("Opción 1.3: Gestión de Profesional");
        int codigo = sc.getInt("Ingrese el código del profesional a actualizar: ");

        int index = this.controller.getById(codigo);
        if (index != -1) {
            Professional professional = this.controller.getByIndex(index);

            sc.printCorrecto("Profesional encontrado:");
            sc.print(this.toString(professional));

            professional.setNombre(this.getNombre());
            professional.setProfession(this.getProfession());

            sc.printCorrecto("Profesional actualizado correctamente");
        } else {
            sc.printAlerta("Profesional no encontrado");
        }
    }

    @Override
    public void delete() {
        sc.printTitulo("Opción 1.4: Eliminación de Profesional");
        int code = sc.getInt("Ingrese el código del profesional a eliminar: ");

        try {
            Professional professional = this.controller.get(code);

            sc.printCorrecto("Profesional encontrado:");
            sc.print(this.toString(professional));

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Profesional no encontrado");
        }
    }

    @Override
    public String toString(Model model) {
        Professional profesional = (Professional) model;

        return super.sc.getVerde("Código: ")  + profesional.getCodigo()
            + super.sc.getVerde(", Nombre: ") + profesional.getNombre()
            + super.sc.getVerde(", Profesión: ") + profesional.getProfession();
    }
}
