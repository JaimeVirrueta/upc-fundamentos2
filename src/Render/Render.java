package Render;

import Controllers.*;
import Models.*;
import Services.Input;
import java.util.ArrayList;

abstract public class Render {

    public Input sc = new Input();

    protected String dashes(int quantity) {
        return this.fill("-", quantity, "-");
    }

    protected String fill(String texto, int longitudTotal, String relleno) {
        return String.format("%" + longitudTotal + "s", texto).replace(' ', relleno.charAt(0));
    }
}
