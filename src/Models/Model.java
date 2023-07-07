package Models;

import java.util.ArrayList;

abstract public class Model {

    private int codigo;
    private String nombre;
    public int getCodigo() {
        return this.codigo;
    }

    public void setCodigo(int nextId) {
        this.codigo = nextId + 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
