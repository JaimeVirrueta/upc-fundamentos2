import Controllers.ProductController;
import Models.Bahia;
import Models.Technical;
import Models.OrdenServicio;
import Models.Product;
import Services.Screen;
import Views.ProductView;

public class Main {

    public static void main(String[] args) {
        Product product = new Product();
        ProductView productView = new ProductView();
        ProductController productController = new ProductController(product, productView);

        Technical technical = new Technical();
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
                    productController.initializeMenu();
                    break;
                case 2:
                    technical.iniciarMenu();
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
                    productController.initData();
                    technical.iniciarData();
                    sc.printCorrecto("Data Inicializada");
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

}