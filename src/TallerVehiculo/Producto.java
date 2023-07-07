package TallerVehiculo;
import TallerVehiculo.Services.Screen;

import java.util.ArrayList;

public class Producto {
    Screen sc = new Screen();
    private static final ArrayList<Producto> lista = new ArrayList<>();
    private int codigo;
    private String nombre;
    private Double precio;

    public Producto() {
    }

    public Producto( String nombre, Double precio) {
        this.setCodigo();
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo() {

        this.codigo = lista.size() + 1;
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

    public String toString() {
        return sc.getVerde("Código: ")  + codigo
                + sc.getVerde(", Nombre: ") + nombre
                + sc.getVerde(", Precio: ") + precio;
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
        double precio = this.inputPrecio();

        Producto nuevoProducto = new Producto(nombre, precio);
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

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Producto producto = lista.get(indice);

            sc.printCorrecto("Producto encontrado:");
            sc.print(producto.toString());

            String nombre = this.inputNombre();
            double precio = this.inputPrecio();

            if (!nombre.equalsIgnoreCase(producto.getNombre())) {
                producto.setNombre(nombre);
            }
            if (precio != producto.getPrecio()) {
                producto.setPrecio(precio);
            }

            sc.printCorrecto("Producto actualizado correctamente");
        } else {
            sc.printAlerta("Producto no encontrado");
        }
    }

    public void eliminar(){
        sc.printTitulo("Opción 1.4: Eliminación de Producto");
        int codigo = sc.getInt("Ingrese el código del producto a eliminar: ");

        int indice = buscarPorCodigo(codigo);
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
        Producto producto1 = new Producto("Aceite Liquimoly 5W30", 250.0);
        lista.add(producto1);
        Producto producto2 = new Producto("Filtro de aire de motor", 20.0);
        lista.add(producto2);
    }

    private int buscarPorCodigo(int codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            if (producto.getCodigo() == codigo) {
                return i; // Se encontró el producto, se devuelve el índice
            }
        }

        return -1; // No se encontró el producto
    }
}
