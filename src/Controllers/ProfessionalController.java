package Controllers;

import Models.Model;
import Models.Professional;
import Views.ProfessionalView;

import java.util.ArrayList;

public class ProfessionalController extends Controller {
    private ArrayList<Professional> professionals = new ArrayList<>();
    private ProfessionalView view;

    public ProfessionalController(ProfessionalView view) {
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Professional> getProfessionals() {
        return this.professionals;
    }

    @Override
    public int modelSize(){
        return this.getProfessionals().size();
    }

    public Professional save(String name, String profession) {
        Professional professional = new Professional(this.modelSize(), name, profession);
        this.getProfessionals().add(professional);

        return professional;
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.getProfessionals().get(i);
            if (model.getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Professional getByIndex(int index) {
        return this.getProfessionals().get(index);
    }

    @Override
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
            if (professional.getName().length() > size) {
                size = professional.getName().length();
            }
        }

        return size;
    }
}
