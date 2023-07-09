package Controllers;

import Models.Model;
import Models.Product;
import Models.Professional;
import Views.ProfessionalView;

import java.util.ArrayList;

public class ProfessionalController extends Controller {

    private ArrayList<Professional> professionals = new ArrayList<>();
    private Professional model;
    private ProfessionalView view;

    public ProfessionalController(Professional model, ProfessionalView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Professional> getProfessionals() {
        return this.professionals;
    }


    public int modelSize(){
        return this.getProfessionals().size();
    }

    public Professional save(String name, String profession) {
        Professional professional = new Professional(this.modelSize(), name, profession);
        this.professionals.add(professional);

        return professional;
    }

    @Override
    public Professional get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.professionals.get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Professional getByIndex(int index) {
        return this.getProfessionals().get(index);
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.getProfessionals().remove(index);
    }

    public void initData() {
        this.save("Piero", "Electricista");
        this.save("Elbert", "Mecánico");
        this.save("Milton", "Electricista");
        this.save("Tio Quijada", "Mecánico");
    }

    public int getNameSize() {
        int size = 0;
        for (Professional professional : this.getProfessionals()) {
            if (professional.getNombre().length() > size) {
                size = professional.getNombre().length();
            }
        }

        return size;
    }
}
