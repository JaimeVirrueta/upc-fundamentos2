package Models;

public class Customer extends Model{

    private String cell_phone;

    public Customer() {
    }

    public Customer(int id, String name, String cell_phone) {
        super.setCodigo(id);
        super.setNombre(name);
        this.cell_phone = cell_phone;
    }

    public String getCellPhone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }
}
