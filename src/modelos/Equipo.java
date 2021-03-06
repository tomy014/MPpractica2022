package modelos;

import java.io.Serializable;

public abstract class Equipo implements Serializable {

    private String nombre;
    private int modDef;
    private int modAtq;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getModDef() {
        return modDef;
    }

    public void setModDef(int modDef) {
        this.modDef = modDef;
    }

    public int getModAtq() {
        return modAtq;
    }

    public void setModAtq(int modAtq) {
        this.modAtq = modAtq;
    }

    public abstract Equipo modificar();

}
