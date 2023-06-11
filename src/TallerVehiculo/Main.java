package TallerVehiculo;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String codigo;
        String nombre;
        Double precio;
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Producto");
            System.out.println("2. Mecanico");
            System.out.println("3. Bahia");
            System.out.println("4. Orden de Servicio");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {

                case 1:
                    Producto.ejecutarProducto();
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
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

}