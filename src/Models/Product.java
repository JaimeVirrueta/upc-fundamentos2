package Models;

public class Product extends Model {

    private String sku;

    private Double price;

    private int stock;

    public Product(int id, String name, String sku, Double price, Integer stock) {
        super.setId(id);
        super.setName(name);
        this.setSku(sku);
        this.setPrice(price);
        this.setStock(stock);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
