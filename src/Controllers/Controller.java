package Controllers;

import Models.Model;

abstract public class Controller {

    public abstract void initializeMenu();
    // public abstract void save(Model model);
    public abstract int getById(int id);

    public abstract Model getByIndex(int index);
}
