package modelos;

import java.util.Date;

public class Desafios {

    private Usuario desafiante;
    private Usuario desafiado;
    private int rondas;
    private Date fecha;
    private int ganador;    //0 = empate; 1 = desafiante; 2 = desafiado
    private int oroGanado;

    public Usuario getDesafiante() {
        return desafiante;
    }

    public void setDesafiante(Usuario desafiante) {
        this.desafiante = desafiante;
    }

    public Usuario getDesafiado() {
        return desafiado;
    }

    public void setDesafiado(Usuario desafiado) {
        this.desafiado = desafiado;
    }

    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getGanador() {
        return ganador;
    }

    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public int getOroGanado() {
        return oroGanado;
    }

    public void setOroGanado(int oroGanado) {
        this.oroGanado = oroGanado;
    }


}
