package Controllers;

import Models.Bay;
import Models.Model;
import Views.BayView;
import java.util.ArrayList;

public class BayController extends Controller {

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
        ArrayList<Model> models = this.model.getModels();
        ArrayList<Bay> bays = new ArrayList<>();

        for (Model model : models) {
            // Realizar casting de Model a Bay
            Bay bay = (Bay) model;
            bays.add(bay);
        }

        return bays;
    }

    public Bay save(String name, String local) {
        Bay bay = new Bay(name, local);
        this.model.save(bay);

        return bay;
    }

    @Override
    public Bay get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        return this.model.getById(id);
    }

    @Override
    public Bay getByIndex(int index) {
        return (Bay) this.model.getByIndex(index);
    }

    public int getByName(String name) {
        return this.model.getByName(name);
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.model.delete(index);
    }

    public void initData() {
        this.save("Bahia interna Derecha", "Av. Universitaria");
        this.save("Bahia interna Izquierda", "Av. Universitaria");
        this.save("Bahia externa", "Av. Universitaria");
    }
}
