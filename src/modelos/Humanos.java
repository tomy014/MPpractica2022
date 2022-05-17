package modelos;

import controlador.Utilidades;

public class Humanos extends Esbirro{

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
