package Models;

public class Client extends Model{

    private String cell_phone;

    public Client() {
    }

    public Client(int id, String name, String cell_phone) {
        super.setCodigo(id);
        super.setNombre(name);
        this.cell_phone = cell_phone;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }
}
