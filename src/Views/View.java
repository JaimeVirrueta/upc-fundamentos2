package Views;

import Controllers.Controller;
import Models.Model;
import Models.Order;
import Services.Screen;
import Services.Table;

import java.util.ArrayList;

abstract public class View {
    protected Screen sc = new Screen();
    protected Table tbl = new Table();

    public int menuOption = 1;
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
