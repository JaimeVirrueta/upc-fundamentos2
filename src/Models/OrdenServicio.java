package Models;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdenServicio {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<OrdenServicio> lista=new ArrayList<>();
    private String cod_ot;
    private String nom_clie;
    private String fech_ot;
    private String hor_ini;
    private String hor_fin;
    private String plac_vehi;
    private Double kilometraje;
    private String tip_serv;
    private String mec_asig;
    private String bahia_ingr;
    private String list_prod;
    private Double pago_base;
    private Double igv=0.18;

    public OrdenServicio(String cod_ot, String nom_clie, String fech_ot, String hor_ini, String hor_fin, String plac_vehi, Double kilometraje, String tip_serv, String mec_asig, String bahia_ingr, String list_prod, Double pago_base) {
        this.cod_ot = cod_ot;
        this.nom_clie = nom_clie;
        this.fech_ot = fech_ot;
        this.hor_ini = hor_ini;
        this.hor_fin = hor_fin;
        this.plac_vehi = plac_vehi;
        this.kilometraje = kilometraje;
        this.tip_serv = tip_serv;
        this.mec_asig = mec_asig;
        this.bahia_ingr = bahia_ingr;
        this.list_prod = list_prod;
        this.pago_base = pago_base;
    }

    public String getCod_ot() {
        return cod_ot;
    }

    public void setCod_ot(String cod_ot) {
        this.cod_ot = cod_ot;
    }

    public String getNom_clie() {
        return nom_clie;
    }

    public void setNom_clie(String nom_clie) {
        this.nom_clie = nom_clie;
    }

    public String getFech_ot() {
        return fech_ot;
    }

    public void setFech_ot(String fech_ot) {
        this.fech_ot = fech_ot;
    }

    public String getHor_ini() {
        return hor_ini;
    }

    public void setHor_ini(String hor_ini) {
        this.hor_ini = hor_ini;
    }

    public String getHor_fin() {
        return hor_fin;
    }

    public void setHor_fin(String hor_fin) {
        this.hor_fin = hor_fin;
    }

    public String getPlac_vehi() {
        return plac_vehi;
    }

    public void setPlac_vehi(String plac_vehi) {
        this.plac_vehi = plac_vehi;
    }

    public Double getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(Double kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getTip_serv() {
        return tip_serv;
    }

    public void setTip_serv(String tip_serv) {
        this.tip_serv = tip_serv;
    }

    public String getMec_asig() {
        return mec_asig;
    }

    public void setMec_asig(String mec_asig) {
        this.mec_asig = mec_asig;
    }

    public String getBahia_ingr() {
        return bahia_ingr;
    }

    public void setBahia_ingr(String bahia_ingr) {
        this.bahia_ingr = bahia_ingr;
    }

    public String getList_prod() {
        return list_prod;
    }

    public void setList_prod(String list_prod) {
        this.list_prod = list_prod;
    }

    public Double getPago_base() {
        return pago_base;
    }

    public void setPago_base(Double pago_base) {
        this.pago_base = pago_base;
    }

    public static void crear(){
        System.out.print("Ingrese el código de OT: ");
        String cod_ot = sc.nextLine();

        System.out.print("Ingrese el nombre del cliente: ");
        String nom_clie = sc.nextLine();

        System.out.print("Ingrese la fecha de OT: ");
        String fech_ot = sc.nextLine();

        System.out.print("Ingrese la hora de inicio: ");
        String hor_ini = sc.nextLine();

        System.out.print("Ingrese la hora de fin: ");
        String hor_fin = sc.nextLine();

        System.out.print("Ingrese la placa del vehiculo: ");
        String plac_vehi = sc.nextLine();

        System.out.print("Ingrese el kilometraje del vehiculo: ");
        double kilometraje = sc.nextDouble();
        sc.nextLine(); // Consumir el salto de línea después de leer el número

        System.out.print("Ingrese el tipo de servicio: (p)Preventivo (c)Correctivo");
        String tip_serv = sc.nextLine();

        System.out.print("Ingrese el mecanico Asignado: ");
        String mec_asig = sc.nextLine();

        System.out.print("Ingrese la bahia donde ingreso el vehiculo: ");
        String bahia_ingr = sc.nextLine();

        System.out.print("Ingrese los productos utilizados: ");
        String list_prod = sc.nextLine();

        System.out.print("Ingrese el precio base: ");
        double pago_base = sc.nextDouble();
        sc.nextLine(); // Consumir el salto de línea después de leer el número


        OrdenServicio nuevoOT = new OrdenServicio(cod_ot, nom_clie,fech_ot, hor_ini, hor_fin, plac_vehi, kilometraje,tip_serv, mec_asig, bahia_ingr, list_prod, pago_base);
        nuevoOT.setCod_ot(cod_ot);
        nuevoOT.setNom_clie(nom_clie);
        nuevoOT.setFech_ot(fech_ot);
        nuevoOT.setHor_ini(hor_ini);
        nuevoOT.setHor_fin(hor_fin);
        nuevoOT.setPlac_vehi(plac_vehi);
        nuevoOT.setKilometraje(kilometraje);
        nuevoOT.setTip_serv(tip_serv);
        nuevoOT.setMec_asig(mec_asig);
        nuevoOT.setBahia_ingr(bahia_ingr);
        nuevoOT.setList_prod(list_prod);
        nuevoOT.setPago_base(pago_base);

        lista.add(nuevoOT);

        System.out.println("OT agregado correctamente");
    }
    public static void listar(){
        if (lista.isEmpty()) {
            System.out.println("No hay OT en la lista");
        } else {
            System.out.println("Lista de OT:");
            for (OrdenServicio ot : lista) {
                System.out.println(ot.toString());
            }
        }

    }
    public static void actualizar(){
        System.out.print("Ingrese el código del ot a actualizar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            OrdenServicio ot = lista.get(indice);

            System.out.println("Producto encontrado:");
            System.out.println(ot);

            System.out.print("Ingrese el nuevo nombre del cliente: ");
            String nuevoNom_clie = sc.nextLine();

            System.out.print("Ingrese la nueva fecha de OT: ");
            String nuevoFech_ot = sc.nextLine();

            System.out.print("Ingrese la nueva hora de inicio: ");
            String nuevoHor_ini = sc.nextLine();

            System.out.print("Ingrese la nueva hora de fin: ");
            String nuevoHor_fin = sc.nextLine();

            System.out.print("Ingrese la nueva placa del vehiculo: ");
            String nuevoPlac_vehi = sc.nextLine();

            System.out.print("Ingrese el nuevo kilometraje del vehiculo: ");
            double nuevoKilometraje = sc.nextDouble();
            sc.nextLine(); // Consumir el salto de línea después de leer el número

            System.out.print("Ingrese el nuevo tipo de servicio: (p)Preventivo (c)Correctivo");
            String nuevoTip_serv = sc.nextLine();
            if (nuevoTip_serv.equals("p")){
                nuevoTip_serv="Preventivo";
            }else nuevoTip_serv="Correctivo";

            System.out.print("Ingrese el nuevo mecanico Asignado: ");
            String nuevoMec_asig = sc.nextLine();

            System.out.print("Ingrese la nueva bahia donde ingreso el vehiculo: ");
            String nuevoBahia_ingr = sc.nextLine();

            System.out.print("Ingrese los nuevos productos utilizados: ");
            String nuevoList_prod = sc.nextLine();

            System.out.print("Ingrese el nuevo precio base: ");
            double nuevoPago_base = sc.nextDouble();
            sc.nextLine(); // Consumir el salto de línea después de leer el número

            ot.setNom_clie(nuevoNom_clie);
            ot.setFech_ot(nuevoFech_ot);
            ot.setHor_ini(nuevoHor_ini);
            ot.setHor_fin(nuevoHor_fin);
            ot.setPlac_vehi(nuevoPlac_vehi);
            ot.setKilometraje(nuevoKilometraje);
            ot.setTip_serv(nuevoTip_serv);
            ot.setMec_asig(nuevoMec_asig);
            ot.setBahia_ingr(nuevoBahia_ingr);
            ot.setList_prod(nuevoList_prod);
            ot.setPago_base(nuevoPago_base);

            System.out.println("OT actualizada correctamente");
        } else {
            System.out.println("OT no encontrada");
        }
    }
    private static int buscarPorCodigo(String codigo) {
        for (int i = 0; i < lista.size(); i++) {
            OrdenServicio ot = lista.get(i);
            if (ot.getCod_ot().equals(codigo)) {
                return i; // Se encontró la OT, se devuelve el índice
            }
        }
        return -1; // No se encontró la OT
    }

    public static void eliminar(){
        System.out.print("Ingrese el código de la OT a eliminar: ");
        String codigo = sc.nextLine();

        int indice = buscarPorCodigo(codigo);
        if (indice != -1) {
            OrdenServicio ot = lista.get(indice);

            System.out.println("OT encontrada:");
            System.out.println(ot);

            lista.remove(indice);

            System.out.println("OT eliminada correctamente");
        } else {
            System.out.println("OT no encontrada");
        }

    }
    public Double calcularPrecioTotal(){
        Double precioTotal=0.0;
        return precioTotal=pago_base+(pago_base*igv);
    }
    public String toString() {
        return "Código: " + cod_ot + "\nNombre: " + nom_clie + "\nFecha de la OT: " + fech_ot+"\nHora de inicio: "+hor_ini+"\nHora de fin: "+hor_fin+
                "\nPlaca del vehiculo: "+plac_vehi+"\nKilometraje: "+kilometraje+"\nTipo de servicio: "+tip_serv+"\nMecanico asignado: "
                +mec_asig+"\nBahia donde ingreso el vehiculo: "+bahia_ingr+"\nProductos utilizados: "+list_prod+"\nPrecio sin igv: "+pago_base+"\nIgv: "+igv+"\nPrecio total "
                +calcularPrecioTotal()+"\n";}

    public static void ejecutarOrdenServicio(){
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Crear OT");
            System.out.println("2. Listar OT");
            System.out.println("3. Actualizar OT");
            System.out.println("4. Eliminar OT");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); // Consumir el salto de línea después de leer el entero

            switch (opcion) {
                case 1:
                    OrdenServicio.crear();
                    break;
                case 2:
                    OrdenServicio.listar();
                    break;
                case 3:
                    OrdenServicio.actualizar();
                    break;
                case 4:
                    OrdenServicio.eliminar();
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
