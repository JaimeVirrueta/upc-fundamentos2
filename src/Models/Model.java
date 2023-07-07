package Models;

import java.util.ArrayList;

abstract public class Model {

    private int codigo;
    private String nombre;
    private static final ArrayList<Model> models = new ArrayList<>();

    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo() {
        this.codigo = this.models.size() + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Métodos para la gestión de la lista de modelos
     */

    public void save(Model model)
    {
        this.models.add(model);
    }

    public Model getByIndex(int index) {
        return this.models.get(index);
    }

    public int getById(int id) {
        for (int i = 0; i < this.models.size(); i++) {
            Model model = this.models.get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<Model> getModels() {
        return models;
    }

    public int modelSize(){
        return this.models.size();
    }

    public void delete(int index) {
        this.models.remove(index);
    }
}
