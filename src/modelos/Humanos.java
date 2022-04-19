package modelos;

import controlador.Utilidades;

public class Humanos extends Esbirro{

    private int lealtad;    //0,1,2 = BAJA,MEDIA,ALTA

    public int getLealtad() {
        return lealtad;
    }

    public void setLealtad(int lealtad) {
        this.lealtad = lealtad;
    }
}
