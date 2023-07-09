package Controllers;

import Models.Model;
import Models.Product;
import Views.ProductView;
import java.util.ArrayList;

public class ProductController extends Controller {
    private ArrayList<Product> products = new ArrayList<>();
    private ProductView view;

    public ProductController(ProductView productView) {
        this.view = productView;
        this.view.setController(this);
    }

    public void initializeMenu() {
        this.view.initializeMenu();
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    @Override
    public int modelSize(){
        return this.getProducts().size();
    }

    public Product save(String name, String sku, double price, int stock) {
        Product product = new Product(this.modelSize(), name, sku, price, stock);
        this.getProducts().add(product);

        return product;
    }

    @Override
    public int getById(int id) {
        for (int i = 0; i < this.modelSize(); i++) {
            Model model = this.getProducts().get(i);
            if (model.getCodigo() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Product getByIndex(int index) {
        return this.getProducts().get(index);
    }

    public int getBySku(String sku) {
        for (int i = 0; i < this.modelSize(); i++) {
            Product product = (Product) this.getByIndex(i);
            if (product.getSku().equalsIgnoreCase(sku)) {
                return i;
            }
        }

        return -1;
    }

    public void delete(int id) {
        int index = this.getById(id);
        this.getProducts().remove(index);
    }

    public void initData() {
        this.save("Aceite Liquimoly 5W30", "ACT001", 250.0, 10);
        this.save("Filtro de aire de motor", "FLT001", 20.0, 12);
        this.save("Filtro de aceite de motor", "FLT002", 50.0, 15);
        this.save("Bujias Bosh", "BUJ001", 50.0, 40);
        this.save("Bujias TORCH", "BUJ002", 50.0, 80);
        this.save("Filtro de aire de cabina", "FLT003", 20.0, 10);
    }

    public int getNameSize() {
        int size = 0;
        for (Product products : this.getProducts()) {
            if (products.getNombre().length() > size) {
                size = products.getNombre().length();
            }
        }

        return size;
    }
}
