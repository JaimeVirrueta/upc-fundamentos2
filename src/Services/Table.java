package Services;

import Controllers.ProfessionalController;
import Models.Professional;

import java.util.ArrayList;

public class Table {

    public Input sc = new Input();

    public void printTable(ArrayList<Professional> professionals, ProfessionalController controller) {
        int size = controller.getNameSize();
        size = Math.max(size, 6);

        String line = "+" + this.dashes(8) + "+" + this.dashes(size + 2) + "+" + this.dashes(15) + "+";

        sc.print(line);
        sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", "Código", "Nombre", "Profesión"));
        sc.print(line);

        for (Professional professional : professionals) {
            int id = professional.getId();
            String name = professional.getName();
            String profession = professional.getProfession();

            sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", id + "", name, profession));
        }

        sc.print(line);
    }

    public void printTable(Professional professional) {
        int size = professional.getName().length();
        size = Math.max(size, 6);

        String line = "+" + this.dashes(8) + "+" + this.dashes(size + 2) + "+" + this.dashes(15) + "+";

        sc.print(line);
        sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", "Código", "Nombre", "Profesión"));
        sc.print(line);
        int id = professional.getId();
        String name = professional.getName();
        String profession = professional.getProfession();
        sc.print(String.format("| %-6s | %-" + size + "s | %-13s |", id + "", name, profession));
        sc.print(line);
    }

    private String fill(String texto, int longitudTotal, String relleno) {
        return String.format("%" + longitudTotal + "s", texto).replace(' ', relleno.charAt(0));
    }

    private String dashes(int quantity) {
        return this.fill("-", quantity, "-");
    }
}
