package Views;

import Controllers.Controller;
import Models.Model;
import Models.Product;
import Services.Screen;

abstract public class View {
    public Screen sc = new Screen();

    public abstract void initializeMenu();

    abstract public void create();

    abstract public void index();

    abstract public void update();

    abstract public void delete();

    abstract public String toString(Model model);
}
