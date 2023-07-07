package Models;

public class Product extends Model {
     private String sku;
    private Double precio;
    private int stock;

    public Product() {
    }

    public Product(int id, String nombre, String sku, Double precio, Integer stock) {
        super.setCodigo(id);
        super.setNombre(nombre);
        this.setSku(sku);
        this.setPrecio(precio);
        this.setStock(stock);
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String nombre) {
        this.sku = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
