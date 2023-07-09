package Views;

import Controllers.ProfessionalController;
import Models.Professional;

public class ProfessionalView extends View{
    private ProfessionalController controller;

    public void setController(ProfessionalController controller) {
        this.controller = controller;
        this.menuOption = 3;
    }

    @Override
    public void initializeMenu() {
        boolean out = false;
        while (!out) {
            input.printTitulo("Opcion "+ this.menuOption +" : Gestión de Profesionales");
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
                    out = true;
                    break;
                default:
                    input.printAlerta("Opción inválida");
            }
        }
    }

    @Override
    public void create(){
        input.printTitulo(this.subTitle("Creación de Profesional"));

        Professional professional = this.controller.save(
                this.inputName(),
                this.inputProfession()
        );

        input.printCorrecto("Profesional creado correctamente");
        render.printTable(professional);
    }

    @Override
    public void index() {
        input.printTitulo(this.subTitle("Listado de Profesionales"));
        if (this.controller.getProfessionals().size() == 0) {
            input.printAlerta("No hay profesionales registrados");
        } else {
            render.printTable(this.controller.getProfessionals(), this.controller);
        }
    }

    @Override
    public void update() {
        input.printTitulo(this.subTitle("Gestión del Profesional."));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Professional professional = this.controller.getByIndex(index);

            input.printCorrecto("Profesional encontrado:");
            render.printTable(professional);

            professional.setName(this.inputName());
            professional.setProfession(this.inputProfession());

            input.printCorrecto("Profesional actualizado correctamente");
        } else {
            input.printAlerta("Profesional no encontrado");
        }
    }

    @Override
    public void delete() {
        input.printTitulo(this.subTitle("Eliminación de Profesional"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Professional professional = (Professional) this.controller.get(code);

            input.printCorrecto("Profesional encontrado:");
            render.printTable(professional);

            String confirmDelete = "";
            while (!confirmDelete.equalsIgnoreCase("S") && !confirmDelete.equalsIgnoreCase("N")) {
                confirmDelete = input.getString("¿Estás seguro de querer eliminar este profesional? (S/N): ");

                if (confirmDelete.equalsIgnoreCase("S")) {
                    this.controller.delete(code);
                    input.printCorrecto("Profesional eliminado correctamente");
                } else if (confirmDelete.equalsIgnoreCase("N")) {
                    input.printAlerta("Eliminación cancelada");
                } else {
                    input.printAlerta("Por favor solo ingresar S/N");
                }
            }
        } catch (Exception e) {
            input.printAlerta("Profesional no encontrado.");
        }
    }

    /**
     * Entrada de datos
     */

    public String inputName() {
        return input.getString("Ingrese el nombre: ");
    }

    public String inputProfession() {
        String valor = input.getString("Ingrese su especialidad " + input.getCian("(m) Mecánico  (e) Electricista") + " : ");

        return this.validateProfession(valor);
    }

    /**
     * Validaciones
     */

    private String validateProfession(String value) {
        if (value.equals("m")) {
            value = "Mecánico";
        } else if (value.equals("e")) {
            value = "Electricista";
        } else {
            input.printAlerta("El valor ingresado es incorrecto");
            value = this.inputProfession();
        }

        return value;
    }

}
