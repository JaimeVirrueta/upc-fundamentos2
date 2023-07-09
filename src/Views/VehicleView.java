package Views;

import Controllers.VehicleController;
import Models.Vehicle;
import Models.Model;

public class VehicleView extends View{

    private VehicleController controller;

    public void setController(VehicleController controller){
        this.controller = controller;
    }
    public String getLicensePlate(){
        String matricula = sc.getString("Ingrese la matricula : ");
        return this.validateLicensePlate(matricula);
    }
    public String getBrand(){
        return sc.getString("Ingrese la marca: ");
    }
    public String getCar_model() {
        return sc.getString("Ingrese el modelo: ");
    }

    private String validateLicensePlate(String matricula) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getByLicensePlate(matricula);
            if (indice != -1) {
                sc.printAlerta("El matricula '" + matricula + "' ya está registrada");

                matricula = this.getLicensePlate();
            } else {
                salir = true;
            }
        }

        return matricula;
    }

    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion 1 : Gestión de Autos");
            sc.printSubtitulo("1. Crear auto");
            sc.printSubtitulo("2. Listar auto");
            sc.printSubtitulo("3. Actualizar auto");
            sc.printSubtitulo("4. Eliminar auto");
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
        sc.printTitulo("Opción 1.1: Creación de Autos");

        Vehicle vehicle = this.controller.save(
                this.getLicensePlate(),
                this.getBrand(),
                this.getCar_model()
        );

        sc.printCorrecto("Auto agregado correctamente");
        sc.print(this.toString(vehicle));
    }
    @Override
    public void index() {
        sc.printTitulo("Opción 1.2: Listado de Autos");
        if (this.controller.getVehicles().size() == 0) {
            sc.printAlerta("No hay autos en la lista");
        } else {
            for (Vehicle vehicle : this.controller.getVehicles()) {
                sc.print(this.toString(vehicle));
            }
        }
    }

    @Override
    public void update() {
        sc.printTitulo("Opción 1.3: Gestión de Autos");
        int codigo = sc.getInt("Ingrese el código del auto a actualizar: ");

        int index = this.controller.getById(codigo);
        if (index != -1) {
            Vehicle vehicle = this.controller.getByIndex(index);

            sc.printCorrecto("Auto encontrado:");
            sc.print(this.toString(vehicle));

            vehicle.setLicense_plate(this.getLicensePlate());
            vehicle.setBrand(this.getBrand());
            vehicle.setModelCar(this.getCar_model());

            sc.printCorrecto("Auto actualizado correctamente");
        } else {
            sc.printAlerta("Auto no encontrado");
        }
    }
    @Override
    public void delete() {
        sc.printTitulo("Opción 1.4: Eliminación de Auto");
        int code = sc.getInt("Ingrese el código del auto a eliminar: ");

        try {
            Vehicle vehicle = this.controller.get(code);

            sc.printCorrecto("Auto encontrado: ");
            sc.print(this.toString(vehicle));

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Auto no encontrado");
        }
    }

    public String toString(Model model) {
        Vehicle vehicle = (Vehicle) model;

        return super.sc.getVerde("Código: ")  + vehicle.getCodigo()
                + super.sc.getVerde(", Matricula: ") + vehicle.getLicensePlate()
                + super.sc.getVerde(", Marca: ") + vehicle.getBrand()
                + super.sc.getVerde(", Modelo: ") + vehicle.getModelCar();
    }
}
