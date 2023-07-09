package Views;

import Controllers.ProfessionalController;
import Models.Professional;

public class ProfessionalView extends View{
    private ProfessionalController controller;
    private int menuOption = 1;
    private int subMenuOption = 0;

    public void setController(ProfessionalController controller) {
        this.controller = controller;
    }

    public String getName() {
        return sc.getString("Ingrese el nombre: ");
    }

    public String getProfession() {
        String valor = sc.getString("Ingrese su especialidad " + sc.getCian("(m) Mecánico  (e) Electricista") + " : ");

        return this.validateProfession(valor);
    }

    private String validateProfession(String value) {
        if (value.equals("m")) {
            value = "Mecánico";
        } else if (value.equals("e")) {
            value = "Electricista";
        } else {
            sc.printAlerta("El valor ingresado es incorrecto");
            value = this.getProfession();
        }

        return value;
    }

    @Override
    public void initializeMenu() {
        boolean out = false;
        while (!out) {
            sc.printTitulo("Opcion 2: Gestión de Profesionales");
            sc.printSubtitulo("1. Crear profesional");
            sc.printSubtitulo("2. Listar profesionales");
            sc.printSubtitulo("3. Actualizar profesional");
            sc.printSubtitulo("4. Eliminar profesional");
            sc.printSubtitulo("5. Salir");
            this.subMenuOption = sc.getInt("   Ingrese una opción: ");

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
                case 5:
                    out = true;
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

    @Override
    public void create(){
        sc.printTitulo(this.subTitle("Creación de Profesional."));

        Professional professional = this.controller.save(getName(), getProfession());

        sc.printCorrecto("Profesional creado correctamente.");
        tbl.printTable(professional);
    }

    @Override
    public void index() {
        sc.printTitulo(this.subTitle("Listado de Profesionales."));
        if (this.controller.getProfessionals().size() == 0) {
            sc.printAlerta("No hay profesionales registrados.");
        } else {
            tbl.printTable(this.controller.getProfessionals(), this.controller);
        }
    }

    @Override
    public void update() {
        sc.printTitulo(this.subTitle("Gestión del Profesional."));
        int code = sc.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Professional professional = this.controller.getByIndex(index);

            sc.printCorrecto("Profesional encontrado:");
            tbl.printTable(professional);

            professional.setNombre(this.getName());
            professional.setProfession(this.getProfession());

            sc.printCorrecto("Profesional actualizado correctamente");
        } else {
            sc.printAlerta("Profesional no encontrado");
        }
    }

    @Override
    public void delete() {
        sc.printTitulo(this.subTitle("Eliminación de Profesional"));
        int code = sc.getInt("Ingrese el código: ");

        try {
            Professional professional = this.controller.get(code);

            sc.printCorrecto("Profesional encontrado:");
            tbl.printTable(professional);

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Profesional no encontrado");
        }
    }

}
