package Controllers;

import Models.Model;
import Models.Professional;
import Views.ProfessionalView;

import java.util.ArrayList;

public class ProfessionalController extends Controller {

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
        ArrayList<Model> models = this.model.getModels();
        ArrayList<Professional> professionals = new ArrayList<>();

        for (Model model : models) {
            // Realizar casting de Model a Product
            Professional professional = (Professional) model;
            professionals.add(professional);
        }

        return professionals;
    }

    public Professional save(String name, String profession) {
        Professional technical = new Professional(name, profession);
        this.model.save(technical);

        return technical;
    }

    @Override
    public Professional get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        return this.model.getById(id);
    }

    @Override
    public Professional getByIndex(int index) {
        return (Professional) this.model.getByIndex(index);
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.model.delete(index);
    }

    public void initData() {
        this.save("Piero", "Electricista");
        this.save("Elbert", "Mecánico");
        this.save("Milton", "Electricista");
        this.save("Tio Quijada", "Mecánico");
    }
}
