package Models;
import java.util.ArrayList;
import java.util.Scanner;
public class Bay extends Model {

    private String local;

    public Bay() { }

    public Bay(int id, String name, String local) {
        super.setCodigo(id);
        super.setNombre(name);
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
