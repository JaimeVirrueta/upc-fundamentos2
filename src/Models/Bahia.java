package Models;
import java.util.ArrayList;
import java.util.Scanner;
public class Bahia {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Bahia> lista=new ArrayList<>();
    private String codigo;
    private String nombre;
    private String local;

    public Bahia(String codigo, String nombre, String local) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.local = local;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public static void crear(){
        System.out.print("Ingrese el código de la bahia: ");
        String codigo = sc.nextLine();

        System.out.print("Ingrese el nombre de la bahia: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el local de la bahia: (sm) San Miguel (sjl) San Juan de Lurigancho (smp)San Martin de Porres");
        String local = sc.nextLine();
        if (local.equals("sm")){
            local="San Miguel";
        }else if (local.equals("sjl")){
            local="San Juan de Lurigancho";
        }else local ="San Martin de Porres";

        Bahia nuevaBahia = new Bahia(codigo,nombre,local);
        nuevaBahia.setCodigo(codigo);
        nuevaBahia.setNombre(nombre);
        nuevaBahia.setLocal(local);

        lista.add(nuevaBahia);

        System.out.println("Bahia agregada correctamente");
    }
    public  static void listar(){
        if (lista.isEmpty()) {
            System.out.println("No hay bahias en la lista");
        } else {
            System.out.println("Lista de bahias:");
            for (Bahia bahia : lista) {
                System.out.println(bahia.toString());
            }
        }

    }
    public  static void actualizar(){
        System.out.print("Ingrese el código de la bahia a actualizar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Bahia bahia = lista.get(indice);

            System.out.println("Bahia encontrada:");
            System.out.println(bahia);

            System.out.print("Ingrese el nuevo nombre de la bahia: ");
            String nuevoNombre = sc.nextLine();

            System.out.println("Ingrese el nuevo local de la bahia: (sm) San Miguel (sjl) San Juan de Lurigancho (smp)San Martin de Porres");
            String nuevoLocal = sc.nextLine();
            if (nuevoLocal.equals("sm")){
                nuevoLocal="San Miguel";
            }else if (nuevoLocal.equals("sjl")){
                nuevoLocal="San Juan de Lurigancho";
            }else nuevoLocal ="San Martin de Porres";

            bahia.setNombre(nuevoNombre);
            bahia.setLocal(nuevoLocal);

            System.out.println("Bahia actualizada correctamente");
        } else {
            System.out.println("Bahia no encontrada");
        }
    }
    private static int buscarPorCodigo(String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Bahia bahia = lista.get(i);
            if (bahia.getCodigo().equals(codigo)) {
                return i; // Se encontró la habia, se devuelve el índice
            }
        }
        return -1; // No se encontró la bahia
    }

    public  static void eliminar(){
        System.out.print("Ingrese el código de la bahia a eliminar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Bahia bahia = lista.get(indice);

            System.out.println("Bahia encontrada:");
            System.out.println(bahia);

            lista.remove(indice);

            System.out.println("Bahia eliminada correctamente");
        } else {
            System.out.println("Bahia no encontrada");
        }

    }
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Local: " + local;
    }
    public static void ejecutarBahia(){
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Crear bahia");
            System.out.println("2. Listar bahia");
            System.out.println("3. Actualizar bahia");
            System.out.println("4. Eliminar bahia");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {

                case 1:
                    Bahia.crear();
                    break;
                case 2:
                    Bahia.listar();
                    break;
                case 3:
                    Bahia.actualizar();
                    break;
                case 4:
                    Bahia.eliminar();
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
