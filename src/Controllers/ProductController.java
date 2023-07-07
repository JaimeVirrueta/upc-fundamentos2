package Controllers;

import Models.Model;
import Models.Product;
import Views.ProductView;

import java.util.ArrayList;

public class ProductController extends Controller {

    private Product model;
    private ProductView view;

    public ProductController(Product product, ProductView productView) {
        this.model = product;
        this.view = productView;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Model> models = this.model.getModels();
        ArrayList<Product> products = new ArrayList<>();

        for (Model model : models) {
            // Realizar casting de Model a Product
            Product product = (Product) model;
            products.add(product);
        }

        return products;
    }

    public Product save(String name, String sku, double precio, int stock) {
        Product product = new Product(name, sku, precio, stock);
        this.model.save(product);

        return product;
    }

    public Product get(int id) {
        int index = this.getById(id);

        return this.getByIndex(index);
    }

    @Override
    public int getById(int id) {
        return this.model.getById(id);
    }

    @Override
    public Product getByIndex(int index) {
        return (Product) this.model.getByIndex(index);
    }

    public int getBySku(String sku) {
        return this.model.getBySku(sku);
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.model.delete(index);
    }


    public void initData() {
        this.save("Aceite Liquimoly 5W30", "ACT001", 250.0, 10);
        this.save("Filtro de aire de motor", "FLT001", 20.0, 12);
        this.save("Filtro de aceite de motor", "FLT002", 50.0, 15);
        this.save("Bujias Bosh", "BUJ001", 50.0, 40);
        this.save("Bujias TORCH", "BUJ002", 50.0, 80);
        this.save("Filtro de aire de cabina", "FLT003", 20.0, 10);
    }
}
