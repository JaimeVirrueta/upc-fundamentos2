package Models;

public class Product extends Model {
     private String sku;
    private Double precio;
    private int stock;

    public Product() {
    }

    public Product(String nombre, String sku, Double precio, Integer stock) {
        super.setCodigo();
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

    public int getBySku(String codigo) {
        for (int i = 0; i < this.modelSize(); i++) {
            Product product = (Product) this.getByIndex(i);
            if (product.getSku().equalsIgnoreCase(codigo)) {
                return i;
            }
        }

        return -1;
    }


}
