package Render;

import Models.Customer;
import Models.Product;
import java.util.ArrayList;

public class ProductRender extends Render {

    private int sizeName = 0;
    private int sizeSku = 0;

    public void printTable(ArrayList<Product> products) {
        this.sizeName = Math.max(this.sizeName(products), 6);
        this.sizeSku = Math.max(this.sizeSku(products), 3);
        this.line();
        this.header();
        this.line();

        for (Product product : products) {
            this.content(product);
        }

        this.line();
    }

    public void printTable(Product product) {
        sizeName = Math.max(product.getName().length(), 6);
        sizeSku = Math.max(product.getSku().length(), 3);

        this.line();
        this.header();
        this.line();
        this.content(product);
        this.line();
    }

    private void content(Product product) {
        String id = product.getIdText();
        String sku = product.getSku();
        String name = product.getName();
        double price = product.getPrice();
        String stock = product.getStock() + "";

        sc.print(String.format(format(), id, sku, name, price, stock ));
    }

    private void line() {
        sc.print("+" + dashes(8) + "+" + dashes(sizeSku+2) + "+" + dashes(sizeName+2) + "+" + dashes(10) + "+" + dashes(5+2) + "+");
    }

    private void header() {
        sc.print(String.format(format(), "Código", "SKU", "Nombre", "Precio", "Stock"));
    }

    private String format() {
        return "| %-6s | %-" + sizeSku + "s | %-" + sizeName + "s | %-8s | %-5s |";
    }

    private int sizeName(ArrayList<Product> products) {
        int size = 0;
        for (Product product : products) {
            if (product.getName().length() > size) {
                size = product.getName().length();
            }
        }

        return size;
    }

    private int sizeSku(ArrayList<Product> products) {
        int size = 0;
        for (Product product : products) {
            if (product.getSku().length() > size) {
                size = product.getSku().length();
            }
        }

        return size;
    }

}
