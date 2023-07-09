package Controllers;

import Models.Bay;
import Models.Model;
import Models.Product;
import Views.BayView;
import java.util.ArrayList;

public class BayController extends Controller {

    private ArrayList<Bay> bays = new ArrayList<>();
    private Bay model;
    private BayView view;

    public BayController(Bay bay, BayView bayView) {
        this.model = bay;
        this.view = bayView;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Bay> getBays() {
        return this.bays;
    }

    public Bay save(String name, String local) {
        Bay bay = new Bay(this.bays.size(), name, local);
        this.bays.add(bay);

        return bay;
    }

    @Override
    public Bay get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.bays.size(); i++) {
            Model model = this.bays.get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Bay getByIndex(int index) {
        return this.bays.get(index);
    }

    public int getByName(String name) {
        for (int i = 0; i < this.bays.size(); i++) {
            Bay bay = (Bay) this.getByIndex(i);
            if (bay.getNombre().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.bays.remove(index);
    }

    public void initData() {
        this.save("Bahia interna Derecha", "Av. Universitaria");
        this.save("Bahia interna Izquierda", "Av. Universitaria");
        this.save("Bahia externa", "Av. Universitaria");
    }
}
