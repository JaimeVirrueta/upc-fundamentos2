import Controllers.BayController;
import Controllers.ProductController;
import Controllers.ProfessionalController;
import Models.Bay;
import Models.Professional;
import Models.OrdenServicio;
import Models.Product;
import Services.Screen;
import Views.BayView;
import Views.ProductView;
import Views.ProfessionalView;

public class Main {

    public static void main(String[] args) {
        Product product = new Product();
        ProductView productView = new ProductView();
        ProductController productController = new ProductController(product, productView);

        Professional professional = new Professional();
        ProfessionalView professionalView = new ProfessionalView();
        ProfessionalController professionalController = new ProfessionalController(professional, professionalView);

        Bay bay = new Bay();
        BayView bayView = new BayView();
        BayController bayController = new BayController(bay, bayView);

        Screen sc = new Screen();

        sc.printTitulo("Bienvenido al Gestor del Taller");
        sc.printCorrecto("===============================");

        String codigo;
        boolean salir = false;

        while (!salir) {
            sc.printTitulo("Menú Principal");
            sc.printSubtitulo("1. Gestión de Órdenes");;
            sc.printSubtitulo("2. Gestión de Productos");
            sc.printSubtitulo("3. Gestión de Profesionales");
            sc.printSubtitulo("4. Gestión de Bahias");
            sc.printSubtitulo("5. Salir");
            int opcion = sc.getInt("   Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    OrdenServicio.ejecutarOrdenServicio();
                    break;
                case 2:
                    productController.initializeMenu();
                    break;
                case 3:
                    professionalController.initializeMenu();
                    break;
                case 4:
                    bayController.initializeMenu();
                    break;
                case 5:
                    salir = true;
                    break;
                case 9:
                    productController.initData();
                    professionalController.initData();
                    bayController.initData();
                    sc.printCorrecto("   Data Inicializada");
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

}