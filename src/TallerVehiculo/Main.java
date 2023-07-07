package TallerVehiculo;
import TallerVehiculo.Services.Screen;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Producto producto = new Producto();
        Screen sc = new Screen();

        sc.printTitulo("Bienvenido al Gestor del Taller");
        sc.printCorrecto("===============================");

        String codigo;
        boolean salir = false;

        while (!salir) {
            sc.printTitulo("Menú Principal");
            sc.printSubtitulo("1. Producto");
            sc.printSubtitulo("2. Mecanico");
            sc.printSubtitulo("3. Bahia");
            sc.printSubtitulo("4. Orden de Servicio");
            sc.printSubtitulo("5. Salir");
            int opcion = sc.getInt("   Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    producto.IniciarMenu();
                    break;
                case 2:
                    Mecanico.ejecutarMecanico();
                    break;
                case 3:
                    Bahia.ejecutarBahia();
                    break;
                case 4:
                    OrdenServicio.ejecutarOrdenServicio();
                    break;
                case 5:
                    salir = true;
                    break;
                case 9:
                    producto.IniciarData();
                    sc.printCorrecto("Data Inicializada");
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

}