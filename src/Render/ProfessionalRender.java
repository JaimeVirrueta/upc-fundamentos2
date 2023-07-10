package Render;

import Models.Professional;

import java.util.ArrayList;

public class ProfessionalRender extends Render {

    private int sizeName = 0;
    private int sizeProfession = 0;

    public void printTable(ArrayList<Professional> professionals) {
        sizeName = Math.max(this.sizeName(professionals), 6);
        sizeProfession = Math.max(this.sizeProfession(professionals), 10);

        this.line();
        this.header();
        this.line();

        for (Professional professional : professionals) {
            this.content(professional);
        }

        this.line();
    }

    public void printTable(Professional professional) {
        sizeName = Math.max(professional.getName().length(), 6);
        sizeProfession = Math.max(professional.getProfession().length(), 10);

        this.line();
        this.header();
        this.line();
        this.content(professional);
        this.line();
    }

    private void content(Professional professional) {
        String id = professional.getIdText();
        String name = professional.getName();
        String profession = professional.getProfession();
        sc.print(String.format(format(), id, name, profession));
    }

    private void line() {
        sc.print("+" + dashes(8) + "+" + dashes(sizeName + 2) + "+" + dashes(sizeProfession + 2) + "+");
    }

    private void header() {
        sc.print(String.format(format(), "Código", "Nombre", "Profesión"));
    }

    private String format() {
        return "| %-6s | %-" + sizeName + "s | %-" + sizeProfession + "s |";
    }

    private int sizeName(ArrayList<Professional> products) {
        int size = 0;
        for (Professional professional : products) {
            if (professional.getName().length() > size) {
                size = professional.getName().length();
            }
        }

        return size;
    }

    private int sizeProfession(ArrayList<Professional> professionals) {
        int size = 0;
        for (Professional professional : professionals) {
            if (professional.getProfession().length() > size) {
                size = professional.getProfession().length();
            }
        }

        return size;
    }

}
