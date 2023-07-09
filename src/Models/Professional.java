package Models;

public class Professional extends Model  {

    private String profession;

    public Professional() {
    }

    public Professional(int id, String name, String profession) {
        super.setCodigo(id);
        super.setNombre(name);
        this.setProfession(profession);
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

}
