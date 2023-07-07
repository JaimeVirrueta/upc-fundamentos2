package TallerVehiculo;
import TallerVehiculo.Services.Screen;

import java.util.ArrayList;

public class Producto{

    Screen sc = new Screen();
    private  static ArrayList<Producto> lista=new ArrayList<>();
    private String codigo;
    private String nombre;
    private Double precio;

    public Producto(){};

    public Producto(String codigo, String nombre, Double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void IniciarMenu(){
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
        String codigo = sc.getString("Ingrese el código: ");

        codigo = this.validarCodigo(codigo);

        String nombre = sc.getString("Ingrese el nombre: ");
        double precio = sc.getDouble("Ingrese el precio: ");

        Producto nuevoProducto = new Producto(codigo, nombre, precio);
        this.lista.add(nuevoProducto);

        sc.printCorrecto("Producto agregado correctamente");
    }

    public String inputCodigo(){
        String codigo = sc.getString("Ingrese el código: ");

        return this.validarCodigo(codigo);
    }

    private String validarCodigo(String codigo) {
        boolean salir = false;
        while (!salir) {
            int indice = buscarPorCodigo(codigo);
            if (indice != -1) {
                sc.printAlerta("El código del producto ya existe");

                codigo = this.inputCodigo();
            } else {
                salir = true;
            }
        }

        return codigo;
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
        String codigo = sc.getString("Ingrese el código del producto a actualizar: ");

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Producto producto = lista.get(indice);

            sc.printCorrecto("Producto encontrado:");
            sc.print(producto.toString());

            String nombre = sc.getString("Ingrese el nuevo nombre del producto: ");
            double precio = sc.getDouble("Ingrese el nuevo precio del producto: ");

            producto.setNombre(nombre);
            producto.setPrecio(precio);

            sc.printCorrecto("Producto actualizado correctamente");
        } else {
            sc.printAlerta("Producto no encontrado");
        }
    }
    private int buscarPorCodigo(String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            if (producto.getCodigo().equals(codigo)) {
                return i; // Se encontró el producto, se devuelve el índice
            }
        }

        return -1; // No se encontró el producto
    }

    public void eliminar(){
        sc.printTitulo("Opción 1.4: Eliminación de Producto");
        String codigo = sc.getString("Ingrese el código del producto a eliminar: ");

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
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Precio: " + precio;
    }
}
