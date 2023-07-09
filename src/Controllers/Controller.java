package Controllers;

import Models.Model;
import Models.Professional;

abstract public class Controller {

    public abstract void initializeMenu();

    public Model get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    public abstract int modelSize();

    public abstract int getById(int id);

    public abstract Model getByIndex(int index);

    public abstract void delete(int id);
}

