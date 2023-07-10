package Views;

import Controllers.ProductController;
import Models.Product;
import Render.ProductRender;

public class ProductView extends View {
    private ProductRender render = new ProductRender();
    private ProductController controller;

    public void setController(ProductController controller) {
        this.controller = controller;
        this.menuOption = 2;
    }

    @Override
    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            input.printTitulo("Opcion "+ this.menuOption +" : Gestión de Productos");
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
        input.printTitulo(this.subTitle("Creación de Producto"));

        Product product = this.controller.save(
                this.inputName(),
                this.inputSku(),
                this.inputPrice(),
                this.inputStock()
        );

        input.printCorrecto("Producto creado correctamente");
        render.printTable(product);
    }

    @Override
    public void index() {
        input.printTitulo(this.subTitle("Listado de Productos"));
        if (this.controller.getProducts().size() == 0) {
            input.printAlerta("No hay productos en la lista");
        } else {
            render.printTable(this.controller.getProducts());
        }
    }

    @Override
    public void update() {
        input.printTitulo(this.subTitle("Gestión del Producto"));
        int code = input.getInt("Ingrese el código: ");

        int index = this.controller.getById(code);
        if (index != -1) {
            Product product = this.controller.getByIndex(index);

            input.printCorrecto("Producto encontrado:");
            render.printTable(product);

            product.setName(this.inputName());
            product.setSku(this.inputSku());
            product.setPrice(this.inputPrice());
            product.setStock(this.inputStock());

            input.printCorrecto("Resultado:");
            render.printTable(product);

            input.printCorrecto("Producto actualizado correctamente");
        } else {
            input.printAlerta("Producto no encontrado");
        }
    }

    @Override
    public void delete() {
        input.printTitulo(this.subTitle("Eliminación de Producto"));
        int code = input.getInt("Ingrese el código: ");

        try {
            Product product = (Product) this.controller.get(code);

            input.printCorrecto("Producto encontrado:");
            render.printTable(product);

            String confirmDelete = "";
            while (!confirmDelete.equalsIgnoreCase("S") && !confirmDelete.equalsIgnoreCase("N")) {
                confirmDelete = input.getString("¿Estás seguro de querer eliminar este producto? (S/N): ");

                if (confirmDelete.equalsIgnoreCase("S")) {
                    this.controller.delete(code);
                    input.printCorrecto("Producto eliminado correctamente");
                } else if (confirmDelete.equalsIgnoreCase("N")) {
                    input.printAlerta("Eliminación cancelada");
                } else {
                    input.printAlerta("Por favor solo ingresar S/N");
                }
            }

        } catch (Exception e) {
            input.printAlerta("Producto no encontrado");
        }
    }

    /**
     * Entrada de datos
     */

    public String inputName() {
        return input.getString("Ingrese el nombre: ");
    }

    public String inputSku() {
        String sku = this.input.getString("Ingrese el código SKU: ");

        return this.validateSku(sku);
    }

    public double inputPrice() {
        return input.getDouble("Ingrese el precio: ");
    }

    public int inputStock() {
        return input.getInt("Ingrese el stock: ");
    }

    /**
     * Validaciones
     */

    private String validateSku(String sku) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getBySku(sku);
            if (indice != -1) {
                input.printAlerta("El SKU del producto ya existe");

                sku = this.inputSku();
            } else {
                salir = true;
            }
        }

        return sku;
    }

}
