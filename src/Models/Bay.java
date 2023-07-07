package Models;
import java.util.ArrayList;
import java.util.Scanner;
public class Bay extends Model {

    private String local;

    public Bay() { }

    public Bay(String name, String local) {
        super.setCodigo();
        super.setNombre(name);
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getByName(String name) {
        for (int i = 0; i < this.modelSize(); i++) {
            Bay bay = (Bay) this.getByIndex(i);
            if (bay.getNombre().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }
}
