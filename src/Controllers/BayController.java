package Controllers;

import Models.Bay;
import Models.Model;
import Views.BayView;
import java.util.ArrayList;

public class BayController extends Controller {
    private ArrayList<Bay> bays = new ArrayList<>();
    private BayView view;

    public BayController(BayView bayView) {
        this.view = bayView;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Bay> getBays() {
        return this.bays;
    }

    @Override
    public int modelSize(){
        return this.getBays().size();
    }

    public Bay save(String name, String local) {
        Bay bay = new Bay(this.modelSize(), name, local);
        this.getBays().add(bay);

        return bay;
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.getBays().get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Bay getByIndex(int index) {
        return this.getBays().get(index);
    }

    public int getByName(String name) {
        for (int i = 0; i < this.modelSize(); i++) {
            Bay bay = this.getByIndex(i);
            if (bay.getNombre().equalsIgnoreCase(name)) {
                return i;
            }
        }

        return -1;
    }

    @Override
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
