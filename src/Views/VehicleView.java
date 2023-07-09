package Views;

import Controllers.VehicleController;
import Models.Vehicle;

public class VehicleView extends View{

    private VehicleController controller;

    public void setController(VehicleController controller){
        this.controller = controller;
        this.menuOption = 6;
    }

    @Override
    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            input.printTitulo("Opcion "+ this.menuOption +" : Gestión de Vehículos");
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
        input.printTitulo(this.subTitle("Creación de Vehículo."));

        Vehicle vehicle = this.controller.save(
                this.inputLicensePlate(),
                this.inputBrand(),
                this.inputModel()
        );

        input.printCorrecto("Vehículo creado correctamente");
        input.print(this.toString(vehicle));
    }

    @Override
    public void index() {
        input.printTitulo(this.subTitle("Listado de Vehículos."));
        if (this.controller.getVehicles().size() == 0) {
            input.printAlerta("No hay autos en la lista");
        } else {
            render.printTable(this.controller.getVehicles(), this.controller);
        }
    }

    @Override
    public void update() {
        input.printTitulo(this.subTitle("Gestión del Vehículo."));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Vehicle vehicle = this.controller.getByIndex(index);

            input.printCorrecto("Vehículo encontrado:");
            input.print(this.toString(vehicle));

            vehicle.setLicensePlate(this.inputLicensePlate());
            vehicle.setBrand(this.inputBrand());
            vehicle.setModelCar(this.inputModel());

            input.printCorrecto("Vehículo actualizado correctamente");
        } else {
            input.printAlerta("Vehículo no encontrado");
        }
    }

    @Override
    public void delete() {
        input.printTitulo(this.subTitle("Eliminación de Vehículo"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Vehicle vehicle = (Vehicle) this.controller.get(code);

            input.printCorrecto("Vehículo encontrado: ");
            input.print(this.toString(vehicle));

            this.controller.delete(code);

        } catch (Exception e) {
            input.printAlerta("Vehículo no encontrado");
        }
    }

    /**
     * Entrada de datos
     */

    public String inputLicensePlate(){
        String matricula = input.getString("Ingrese el número de placa : ");

        return this.validateLicensePlate(matricula);
    }

    public String inputBrand(){
        return input.getString("Ingrese la marca: ");
    }

    public String inputModel() {
        return input.getString("Ingrese el modelo: ");
    }

    /**
     * Validaciones
     */

    private String validateLicensePlate(String matricula) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getByLicensePlate(matricula);
            if (indice != -1) {
                input.printAlerta("El número de placa '" + matricula + "' ya está registrada");
                matricula = this.inputLicensePlate();
            } else {
                salir = true;
            }
        }

        return matricula;
    }

}
