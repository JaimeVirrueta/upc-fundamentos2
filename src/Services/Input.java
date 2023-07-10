package Services;

import java.util.Scanner;

public class Input {

    Scanner sc = new Scanner(System.in);

    public Input(){};

    public void print(String texto){
        System.out.println(texto);
    }

    public void printTitulo(String texto){
        System.out.println("\u001B[34m\n" + texto + "\u001B[0m");
    }

    public void printSubtitulo(String texto){
        System.out.println(this.getVerde(texto));
    }

    public String getVerde(String texto){
        return "\u001B[32m" + texto + "\u001B[0m";
    }

    public String getCian(String texto){
        return "\u001B[36m" + texto + "\u001B[0m";
    }

    public void printAlerta(String texto){
        System.out.println("\u001B[33m¡" + texto + "!\u001B[0m");
    }

    public void printCorrecto(String texto){
        System.out.println(this.getCian(texto));
    }

    public void printInput(String text){
        System.out.print(text);
    }

    public int getInt(String text){
        this.printInput(text);
        int value = sc.nextInt();
        this.sc.nextLine();

        return value;
    }

    public String getString(String text) {
        this.printInput(text);
        String value = this.sc.nextLine();

        return value;
    }

    public double getDouble(String text) {
        this.printInput(text);
        double value = sc.nextDouble();

        return value;
    }
}
