package modelos;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

    private String nick;
    private String nombre;
    private String password;
    private String numReg;
    private boolean baneado;
    protected Personaje pj;
    //tienen 1 personaje, podría no tenerlo si lo da de baja.

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
}
