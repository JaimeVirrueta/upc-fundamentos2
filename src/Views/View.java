package Views;

import Services.Input;
import Services.Render;

abstract public class View {

    protected Input input = new Input();

    protected Render render = new Render();

    public int menuOption = 0;

    public int subMenuOption = 0;

    public abstract void initializeMenu();

    abstract public void create();

    abstract public void index();

    abstract public void update();

    abstract public void delete();

    public String subTitle(String txt) {
        return "Opción " + this.menuOption + "." + this.subMenuOption + ": " + txt;
    }

}
