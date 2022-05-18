package modelos;

import controlador.Utilidades;

import java.io.Serializable;

public class Humanos extends Esbirro implements Serializable {

    private int lealtad;    //0,1,2 = BAJA,MEDIA,ALTA

    public Humanos(String nombre, int salud, int lealtad) {
        super(nombre, salud);
        this.lealtad = lealtad;
    }

    public int getLealtad() {
        return lealtad;
    }

    public void setLealtad(int lealtad) {
        this.lealtad = lealtad;
    }
}
