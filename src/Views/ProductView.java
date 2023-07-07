package Views;

import Controllers.ProductController;
import Models.Model;
import Models.Product;

public class ProductView extends View{
    private ProductController controller;

    public void setController(ProductController controller) {
        this.controller = controller;
    }

    public String getNombre() {
        return sc.getString("Ingrese el nombre: ");
    }

    public String getSku() {
        String sku = this.sc.getString("Ingrese el código SKU: ");

        return this.validateSku(sku);
    }

    private String validateSku(String sku) {
        boolean salir = false;
        while (!salir) {
            int indice = this.controller.getBySku(sku);
            if (indice != -1) {
                sc.printAlerta("El SKU del producto ya existe");

                sku = this.getSku();
            } else {
                salir = true;
            }
        }

        return sku;
    }

    public double getPrecio() {
        return sc.getDouble("Ingrese el precio: ");
    }

    public int getStock() {
        return sc.getInt("Ingrese el stock: ");
    }

    public void initializeMenu() {
        boolean salir = false;
        while (!salir) {
            sc.printTitulo("Opcion 1 : Gestión de Productos");
            sc.printSubtitulo("1. Crear producto");
            sc.printSubtitulo("2. Listar productos");
            sc.printSubtitulo("3. Actualizar producto");
            sc.printSubtitulo("4. Eliminar producto");
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
        sc.printTitulo("Opción 1.1: Creación de Producto");

        String nombre = this.getNombre();
        String sku = this.getSku();
        double precio = this.getPrecio();
        int stock = this.getStock();
        Product product = this.controller.save(nombre, sku, precio, stock);

        sc.printCorrecto("Producto agregado correctamente");
        sc.print(this.toString(product));
    }

    @Override
    public void index() {
        sc.printTitulo("Opción 1.2: Listado de Productos");
        if (this.controller.getProducts().size() == 0) {
            sc.printAlerta("No hay productos en la lista");
        } else {
            for (Product product : this.controller.getProducts()) {
                sc.print(this.toString(product));
            }
        }
    }

    @Override
    public void update() {
        sc.printTitulo("Opción 1.3: Gestión de Producto");
        int codigo = sc.getInt("Ingrese el código del producto a actualizar: ");

        int index = this.controller.getById(codigo);
        if (index != -1) {
            Product product = this.controller.getByIndex(index);

            sc.printCorrecto("Producto encontrado:");
            sc.print(this.toString(product));

            product.setNombre(this.getNombre());
            product.setSku(this.getSku());
            product.setPrecio(this.getPrecio());
            product.setStock(this.getStock());

            sc.printCorrecto("Producto actualizado correctamente");
        } else {
            sc.printAlerta("Producto no encontrado");
        }
    }

    @Override
    public void delete() {
        sc.printTitulo("Opción 1.4: Eliminación de Producto");
        int code = sc.getInt("Ingrese el código del producto a eliminar: ");

        try {
            Product product = this.controller.get(code);

            sc.printCorrecto("Producto encontrado:");
            sc.print(this.toString(product));

            this.controller.delete(code);

        } catch (Exception e) {
            sc.printAlerta("Producto no encontrado");
        }
/*
        int index = this.controller.getById(codigo);
        if (index != -1) {
            Product product = this.controller.getByIndex(index);

            sc.printCorrecto("Producto encontrado:");
            sc.print(this.toString(product));

            this.lista.remove(indice);

            sc.printCorrecto("Producto eliminado correctamente");
        } else {
            sc.printAlerta("Producto no encontrado");
        }*/
    }

    @Override
    public String toString(Model model) {
        Product product = (Product) model;

        return super.sc.getVerde("Código: ")  + product.getCodigo()
            + super.sc.getVerde(", SKU: ") + product.getSku()
            + super.sc.getVerde(", Nombre: ") + product.getNombre()
            + super.sc.getVerde(", Stock: ") + product.getStock()
            + super.sc.getVerde(", Precio: ") + product.getPrecio();
    }
}
