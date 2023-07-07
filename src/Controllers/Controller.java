package Controllers;

import Models.Model;
import Models.Product;

abstract public class Controller {

    public abstract void initializeMenu();

    public abstract Model get(int id);

    public abstract int getById(int id);

    public abstract Model getByIndex(int index);

    public abstract void delete(int id);
}
