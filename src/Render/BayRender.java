package Render;

import Models.Bay;
import java.util.ArrayList;

public class BayRender extends Render {

    private int sizeName = 0;
    private int sizeLocal = 0;

    public void printTable(ArrayList<Bay> bays) {
        sizeName = Math.max(this.sizeName(bays), 6);
        sizeLocal = Math.max(this.sizeLocal(bays), 5);

        this.line();
        this.header();
        this.line();

        for (Bay bay : bays) {
            this.content(bay);
        }

        this.line();
    }

    public void printTable(Bay bay) {
        sizeName = Math.max(bay.getName().length(), 6);
        sizeLocal = Math.max(bay.getName().length(), 5);

        this.line();
        this.header();
        this.line();
        this.content(bay);
        this.line();
    }

    private void content(Bay bay) {
        String id = bay.getIdText();
        String name = bay.getName();
        String profession = bay.getLocal();
        sc.print(String.format("| %-6s | %-" + sizeName + "s | %-" + sizeLocal + "s |", id, name, profession));
    }

    protected void line() {
        sc.print("+" + this.dashes(8) + "+" + this.dashes(sizeName + 2) + "+" + this.dashes(sizeLocal + 2) + "+");
    }

    private void header() {
        sc.print(String.format("| %-6s | %-" + sizeName + "s | %-" + sizeLocal + "s |", "Código", "Nombre", "Local"));
    }

    private int sizeName(ArrayList<Bay> bays) {
        int size = 0;
        for (Bay bay : bays) {
            if (bay.getName().length() > size) {
                size = bay.getName().length();
            }
        }

        return size;
    }

    private int sizeLocal(ArrayList<Bay> bays) {
        int size = 0;
        for (Bay bay : bays) {
            if (bay.getLocal().length() > size) {
                size = bay.getLocal().length();
            }
        }

        return size;
    }
}
