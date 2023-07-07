package TallerVehiculo;
import TallerVehiculo.Services.Screen;

import java.util.ArrayList;

public class Producto {
    Screen sc = new Screen();
    private static final ArrayList<Producto> lista = new ArrayList<>();
    private int codigo;
    private String sku;
    private String nombre;
    private Double precio;
    private int stock;

    public Producto() {
    }

    public Producto( String nombre, String sku, Double precio, Integer stock) {
        this.setCodigo();
        this.setNombre(nombre);
        this.setSku(sku);
        this.setPrecio(precio);
        this.setStock(stock);
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo() {

        this.codigo = lista.size() + 1;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String nombre) {
        this.sku = nombre;
    }

    public String inputSku() {
        String sku = sc.getString("Ingrese el código SKU: ");

        return this.validarSku(sku);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String inputNombre() {
        return sc.getString("Ingrese el nombre: ");
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public double inputPrecio() {
        return sc.getDouble("Ingrese el precio: ");
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int inputStock() {
        return sc.getInt("Ingrese el stock: ");
    }

    public String toString() {
        return sc.getVerde("Código: ")  + this.getCodigo()
                + sc.getVerde(", SKU: ") + this.getSku()
                + sc.getVerde(", Nombre: ") + this.getNombre()
                + sc.getVerde(", Stock: ") + this.getStock()
                + sc.getVerde(", Precio: ") + this.getPrecio();
    }

    public void IniciarMenu() {
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
        sc.printTitulo("Opción 1.1: Creación de Producto");
        String nombre = this.inputNombre();
        String sku = this.inputSku();
        double precio = this.inputPrecio();
        int stock = this.inputStock();

        Producto nuevoProducto = new Producto(nombre, sku, precio, stock);
        this.lista.add(nuevoProducto);

        sc.printCorrecto("Producto agregado correctamente");
    }

    public void listar(){
        sc.printTitulo("Opción 1.2: Listado de Productos");
        if (lista.isEmpty()) {
            sc.printAlerta("No hay productos en la lista");
        } else {
            for (Producto producto : lista) {
                sc.print(producto.toString());
            }
        }

    }

    public void actualizar(){
        sc.printTitulo("Opción 1.3: Gestión de Producto");
        int codigo = sc.getInt("Ingrese el código del producto a actualizar: ");

        int indice = buscar(codigo);
        if (indice != -1) {
            Producto producto = lista.get(indice);

            sc.printCorrecto("Producto encontrado:");
            sc.print(producto.toString());

            String nombre = this.inputNombre();
            String sku = this.inputSku();
            double precio = this.inputPrecio();
            int stock = this.inputStock();

            producto.setNombre(nombre);
            producto.setSku(sku);
            producto.setPrecio(precio);
            producto.setStock(stock);

            sc.printCorrecto("Producto actualizado correctamente");
        } else {
            sc.printAlerta("Producto no encontrado");
        }
    }

    public void eliminar(){
        sc.printTitulo("Opción 1.4: Eliminación de Producto");
        int codigo = sc.getInt("Ingrese el código del producto a eliminar: ");

        int indice = buscar(codigo);
        if (indice != -1) {
            Producto producto = this.lista.get(indice);

            sc.printCorrecto("Producto encontrado:");
            sc.print(producto.toString());

            this.lista.remove(indice);

            sc.printCorrecto("Producto eliminado correctamente");
        } else {
            sc.printAlerta("Producto no encontrado");
        }

    }

    public void IniciarData() {
        Producto producto1 = new Producto("Aceite Liquimoly 5W30", "ACT001", 250.0, 10);
        lista.add(producto1);
        Producto producto2 = new Producto("Filtro de aire de motor", "FLT001", 20.0, 12);
        lista.add(producto2);
        Producto producto3 = new Producto("Filtro de aceite de motor", "FLT002", 50.0, 15);
        lista.add(producto3);
        Producto producto4 = new Producto("Bujias Bosh", "BUJ001", 50.0, 40);
        lista.add(producto4);
        Producto producto5 = new Producto("Bujias TORCH", "BUJ002", 50.0, 80);
        lista.add(producto5);
        Producto producto6 = new Producto("Filtro de aire de cabina", "FLT003", 20.0, 10);
        lista.add(producto6);
    }

    private int buscar(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            if (producto.getCodigo() == codigo) {
                return i; // Se encontró el producto, se devuelve el índice
            }
        }

        return -1; // No se encontró el producto
    }

    private int buscar(String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            if (producto.getSku().equalsIgnoreCase(codigo)) {
                return i; // Se encontró el producto, se devuelve el índice
            }
        }

        return -1; // No se encontró el producto
    }

    private String validarSku(String sku) {
        boolean salir = false;
        while (!salir) {
            int indice = buscar(sku);
            if (indice != -1) {
                sc.printAlerta("El SKU del producto ya existe");

                sku = this.inputSku();
            } else {
                salir = true;
            }
        }

        return sku;
    }
}
