package TallerVehiculo;
import java.util.ArrayList;
import java.util.Scanner;
public class Producto{
    private  static Scanner sc = new Scanner(System.in);
    private  static ArrayList<Producto> lista=new ArrayList<>();
    public String codigo;
    public String nombre;
    public Double precio;

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

    public static void crear(){
        System.out.print("Ingrese el código del producto: ");
        String codigo = sc.nextLine();

        System.out.print("Ingrese el nombre del producto: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese el precio del producto: ");
        double precio = sc.nextDouble();
        sc.nextLine(); // Consumir el salto de línea después de leer el número

        Producto nuevoProducto = new Producto(codigo,nombre,precio);
        nuevoProducto.setCodigo(codigo);
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setPrecio(precio);

        lista.add(nuevoProducto);

        System.out.println("Producto agregado correctamente");
    }
    public static void listar(){
        if (lista.isEmpty()) {
            System.out.println("No hay productos en la lista");
        } else {
            System.out.println("Lista de productos:");
            for (Producto producto : lista) {
                System.out.println(producto.toString());
            }
        }

    }
    public static void actualizar(){
        System.out.print("Ingrese el código del producto a actualizar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Producto producto = lista.get(indice);

            System.out.println("Producto encontrado:");
            System.out.println(producto);

            System.out.print("Ingrese el nuevo nombre del producto: ");
            String nuevoNombre = sc.nextLine();

            System.out.print("Ingrese el nuevo precio del producto: ");
            double nuevoPrecio = sc.nextDouble();
            sc.nextLine(); // Consumir el salto de línea después de leer el número

            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);

            System.out.println("Producto actualizado correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }
    }
    private static int buscarPorCodigo(String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Producto producto = lista.get(i);
            if (producto.getCodigo().equals(codigo)) {
                return i; // Se encontró el producto, se devuelve el índice
            }
        }
        return -1; // No se encontró el producto
    }

    public static void eliminar(){
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Producto producto = lista.get(indice);

            System.out.println("Producto encontrado:");
            System.out.println(producto);

            lista.remove(indice);

            System.out.println("Producto eliminado correctamente");
        } else {
            System.out.println("Producto no encontrado");
        }

    }
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Precio: " + precio;
    }

    public static void ejecutarProducto(){
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Crear producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {
                case 1:
                    Producto.crear();
                    break;
                case 2:
                    Producto.listar();
                    break;
                case 3:
                    Producto.actualizar();
                    break;
                case 4:
                    Producto.eliminar();
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
