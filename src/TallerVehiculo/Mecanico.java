package TallerVehiculo;
import java.util.ArrayList;
import java.util.Scanner;

public class Mecanico {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Mecanico> lista=new ArrayList<>();
    private String codigo;
    private String nombre;
    private String especialidad;

    public Mecanico(String codigo, String nombre, String especialidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especialidad = especialidad;
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

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public static void crear(){
        System.out.print("Ingrese el código del mecanico: ");
        String codigo = sc.nextLine();

        System.out.print("Ingrese el nombre del mecanico: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la especialidad del mecanico: (m) Mecanico (e) Electronico ");
        String especialidad = sc.nextLine();
        if (especialidad.equals("m")){
            especialidad="Mecanico";
        }else especialidad="Electronico";

        Mecanico nuevoMecanico = new Mecanico(codigo,nombre,especialidad);
        nuevoMecanico.setCodigo(codigo);
        nuevoMecanico.setNombre(nombre);
        nuevoMecanico.setEspecialidad(especialidad);

        lista.add(nuevoMecanico);

        System.out.println("mecanico agregado correctamente");
    }
    public  static void listar(){
        if (lista.isEmpty()) {
            System.out.println("No hay mecanicos en la lista");
        } else {
            System.out.println("Lista de mecanicos:");
            for (Mecanico mecanico : lista) {
                System.out.println(mecanico.toString());
            }
        }

    }
    public  static void actualizar(){
        System.out.print("Ingrese el código del mecanico a actualizar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Mecanico mecanico = lista.get(indice);

            System.out.println("Mecanico encontrado:");
            System.out.println(mecanico);

            System.out.print("Ingrese el nuevo nombre del mecanico: ");
            String nuevoNombre = sc.nextLine();

            System.out.println("Ingrese la nueva especialidad del mecanico: (m) Mecanico (e) Electronico ");
            String nuevaEspecialidad = sc.nextLine();
            if (nuevaEspecialidad.equals("m")){
                nuevaEspecialidad="Mecanico";
            }else nuevaEspecialidad="Electronico";

            mecanico.setNombre(nuevoNombre);
            mecanico.setEspecialidad(nuevaEspecialidad);

            System.out.println("Mecanico actualizado correctamente");
        } else {
            System.out.println("Mecanico no encontrado");
        }
    }
    private static int buscarPorCodigo(String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            Mecanico mecanico = lista.get(i);
            if (mecanico.getCodigo().equals(codigo)) {
                return i; // Se encontró el mecanico, se devuelve el índice
            }
        }
        return -1; // No se encontró el mecanico
    }

    public  static void eliminar(){
        System.out.print("Ingrese el código del mecanico a eliminar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            Mecanico mecanico = lista.get(indice);

            System.out.println("Mecanico encontrado:");
            System.out.println(mecanico);

            lista.remove(indice);

            System.out.println("Mecanico eliminado correctamente");
        } else {
            System.out.println("Mecanico no encontrado");
        }

    }
    public String toString() {
        return "Código: " + codigo + ", Nombre: " + nombre + ", Especialidad: " + especialidad;
    }
    public static void ejecutarMecanico(){
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Crear mecanico");
            System.out.println("2. Listar mecanico");
            System.out.println("3. Actualizar mecanico");
            System.out.println("4. Eliminar mecanico");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {

                case 1:
                    Mecanico.crear();
                    break;
                case 2:
                    Mecanico.listar();
                    break;
                case 3:
                    Mecanico.actualizar();
                    break;
                case 4:
                    Mecanico.eliminar();
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
