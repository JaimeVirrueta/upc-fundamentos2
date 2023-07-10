package Models;

abstract public class Model {

    private int id;

    private String name;

    public int getId() {
        return this.id;
    }

    public String getIdText() {
        return this.id + "";
    }

    public void setId(int nextId) {
        this.id = nextId + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
