package Models;
import Services.Screen;

import java.util.ArrayList;

public class Technical extends Model  {
    Screen sc = new Screen();
    static ArrayList<Technical> lista = new ArrayList<>();
    private String especialidad;

    public Technical() {
    }

    public Technical(String nombre, String especialidad) {
        this.setCodigo();
        this.setNombre(nombre);
        this.setEspecialidad(especialidad);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String inputEspecialidad() {
        String valor = sc.getString("Ingrese la especialidad: (m)  Mecánico (e) Electricista");

        return this.validarEspecialidad(valor);
    }

    private String validarEspecialidad(String valor) {
        if (valor.equals("m")) {
            valor = "Mecánico";
        } else if (valor.equals("e")) {
            valor = "Electricista";
        } else {
            sc.printAlerta("El valor ingresado es incorrecto");
            valor = this.inputEspecialidad();
        }

        return valor;
    }


    public String toString() {
        return sc.getVerde("Código: ")  + this.getCodigo()
                + sc.getVerde(", Nombre: ") + this.getNombre()
                + sc.getVerde(", Especialidad: ") + this.getEspecialidad();
    }

    public void iniciarMenu(){
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion 1 : Gestión de Mecánicos");
            sc.printSubtitulo("1. Crear mecanico");
            sc.printSubtitulo("2. Listar mecanicos");
            sc.printSubtitulo("3. Actualizar mecanico");
            sc.printSubtitulo("4. Eliminar mecanico");
            sc.printSubtitulo("5. Salir");
            int opcion = sc.getInt("   Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    this.crear();
                    break;
                case 2:
                    this.listar();
                    break;
                case 3:
                    this.actualizar();
                    break;
                case 4:
                    this.eliminar();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

    public void crear(){
        sc.printTitulo("Opción 1.1: Creación de Mecánico");

        Technical nuevoTechnical = new Technical(
            "this.inputNombre()",
            this.inputEspecialidad()
        );
        this.lista.add(nuevoTechnical);

        sc.printCorrecto("Mecanico agregado correctamente");
    }

    public void listar(){
        sc.printTitulo("Opción 1.2: Listado de Mecánicos");
        if (lista.isEmpty()) {
            sc.printAlerta("No hay mecanicos registrados");
        } else {
            for (Technical technical : lista) {
                sc.print(technical.toString());
            }
        }
    }

    public void actualizar(){
        sc.printTitulo("Opción 1.3: Gestión de Mecánico");
        int codigo = sc.getInt("Ingrese el código del registro a actualizar: ");

        int indice = this.buscar(codigo);
        if (indice != -1) {
            Technical technical = lista.get(indice);

            sc.printCorrecto("Mecánico encontrado:");
            sc.print(technical.toString());

            //technical.setNombre(this.inputNombre());
            technical.setEspecialidad(this.inputEspecialidad());

            sc.printCorrecto("Mecánico actualizado correctamente");
        } else {
            sc.printAlerta("Mecánico no encontrado");
        }
    }

    public void eliminar(){
        sc.printTitulo("Opción 1.4: Eliminación de Mecánico");
        int codigo = sc.getInt("Ingrese el código del registro a eliminar: ");

        int indice = buscar(codigo);
        if (indice != -1) {
            Technical technical = this.lista.get(indice);

            sc.printCorrecto("Mecánico encontrado:");
            sc.print(technical.toString());

            this.lista.remove(indice);

            sc.printCorrecto("Mecánico eliminado correctamente");
        } else {
            sc.printAlerta("Mecánico no encontrado");
        }
    }

    public void iniciarData() {
        Technical mec1 = new Technical("Piero", this.validarEspecialidad("e"));
        this.lista.add(mec1);
        Technical mec2 = new Technical("Elbert", this.validarEspecialidad("m"));
        this.lista.add(mec2);
        Technical mec3 = new Technical("Milton", this.validarEspecialidad("e"));
        this.lista.add(mec3);
        Technical mec4 = new Technical("Tio Quijada", this.validarEspecialidad("m"));
        this.lista.add(mec4);
    }

    private int buscar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Technical technical = lista.get(i);
            if (technical.getCodigo() == codigo) {
                return i; // Se encontró el mecanico, se devuelve el índice
            }
        }

        return -1; // No se encontró el mecanico
    }

}
