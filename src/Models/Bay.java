package Models;

public class Bay extends Model {

    private String local;

    public Bay(int id, String name, String local) {
        super.setId(id);
        super.setName(name);
        this.local = local;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}
