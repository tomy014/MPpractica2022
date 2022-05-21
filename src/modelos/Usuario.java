package modelos;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    private String nick;
    private String nombre;
    private String password;
    private String numReg;
    private boolean baneado;
    private int oro;
    protected Personaje pj;
    //tienen 1 personaje, podría no tenerlo si lo da de baja.

    public void rellenarDatos(){
        this.baneado=false;
        this.pj = null;
        this.oro = 500;
        this.numReg = crearRegistro();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumReg() {
        return numReg;
    }

    public void setNumReg(String numReg) {
        this.numReg = numReg;
    }

    public boolean isBaneado() {
        return baneado;
    }

    public void setBaneado(boolean baneado) {
        this.baneado = baneado;
    }

    public Personaje getPj() {
        return pj;
    }

    public void setPj(Personaje pj) {
        this.pj = pj;
        if (this.pj!=null)
            this.setOro(getPj().getOro());
        else
            this.setOro(0);
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }

    private String crearRegistro() {
        String cadena="";
        /*cadena+=Utilidades.letraAleatoria();
        cadena+=Utilidades.numAleatorio();
        cadena+=Utilidades.numAleatorio();
        cadena+=Utilidades.letraAleatoria();
        cadena+=Utilidades.letraAleatoria();
         */
        cadena="l12vw";
        cadena.toUpperCase();
        return cadena;
    }
}
