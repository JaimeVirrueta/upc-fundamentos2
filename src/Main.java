import Controllers.*;
import Models.*;
import Services.Screen;
import Views.*;

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

        Client client = new Client();
        ClientView clientView = new ClientView();
        ClientController clientController = new ClientController(client, clientView);

        Car car = new Car();
        CarView carView = new CarView();
        CarController carController = new CarController(car, carView);

        Order order = new Order();
        OrderView orderView = new OrderView();
        OrderController orderController = new OrderController(order, orderView);
        orderController.setControllers(clientController, professionalController, bayController, carController,productController);

        productView.setController(productController);

        // Establecer los controladores en la vista de órdenes
        orderView.setController(orderController);
        orderView.setProductController(productController);
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
            sc.printSubtitulo("5. Gestión de Clientes");
            sc.printSubtitulo("6. Gestión de Autos");
            sc.printSubtitulo("7. Salir");
            int opcion = sc.getInt("   Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    orderController.initializeMenu();
                    break;
                case 2:
                    productController.initializeMenu();
                    break;
                case 3:
                    orderController.initializeMenu();
                    break;
                case 4:
                    bayController.initializeMenu();
                    break;
                case 5:
                    clientController.initializeMenu();
                    break;
                case 6:
                    carController.initializeMenu();
                    break;
                case 7:
                    salir = true;
                    break;
                case 9:
                    productController.initData();
                    professionalController.initData();
                    bayController.initData();
                    clientController.initData();
                    carController.initData();
                    orderController.initData();

                    sc.printCorrecto("   Data Inicializada");
                    break;
                default:
                    sc.printAlerta("Opción inválida");
            }
        }
    }

}