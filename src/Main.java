import TallerVehiculo.Bahia;
import TallerVehiculo.Mecanico;
import TallerVehiculo.OrdenServicio;
import TallerVehiculo.Producto;
import TallerVehiculo.Services.Screen;

public class Main {

    public static void main(String[] args) {
        Producto producto = new Producto();
        Mecanico mecanico = new Mecanico();
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
                    producto.iniciarMenu();
                    break;
                case 2:
                    mecanico.iniciarMenu();
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
                    producto.iniciarData();
                    mecanico.iniciarData();
                    sc.printCorrecto("Data Inicializada");
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

}