package modelos;

import java.io.Serializable;
import java.time.LocalDate;

public class Desafio implements Serializable {

    private boolean validado = false;
    private Usuario desafiante;
    private Usuario desafiado;
    private int rondas;
    private LocalDate fecha;
    private int ganador;    //0 = empate; 1 = desafiante; 2 = desafiado
    private int oroApostado;
    private int oroGanado;

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * 0 = empate; 1 = desafiante; 2 = desafiado
     * @return 0 = empate; 1 = desafiante; 2 = desafiado
     */
    public int getGanador() {
        return ganador;
    }

    /**
     * 0 = empate; 1 = desafiante; 2 = desafiado
     * @param ganador 0 = empate; 1 = desafiante; 2 = desafiado
     */
    public void setGanador(int ganador) {
        this.ganador = ganador;
    }

    public int getOroGanado() {
        return oroGanado;
    }

    public void setOroGanado(int oroGanado) {
        this.oroGanado = oroGanado;
    }

    public int getOroApostado() {
        return oroApostado;
    }

    public void setOroApostado(int oroApostado) {
        this.oroApostado = oroApostado;
    }
}
