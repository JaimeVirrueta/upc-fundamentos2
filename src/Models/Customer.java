package Models;

public class Customer extends Model{

    private String phone;

    public Customer(int id, String name, String phone) {
        super.setId(id);
        super.setName(name);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
