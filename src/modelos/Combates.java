package modelos;

import java.util.Date;
import java.util.List;

public class Combates {

    private String desafiante;
    private String desafiado;
    private int rondas;
    private Date fecha;
    private String ganador;
    private List<String> conEsbirros;
    private int oro;

    public String getDesafiante() {
        return desafiante;
    }

    public void setDesafiante(String desafiante) {
        this.desafiante = desafiante;
    }

    public String getDesafiado() {
        return desafiado;
    }

    public void setDesafiado(String desafiado) {
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

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public List<String> getConEsbirros() {
        return conEsbirros;
    }

    public void setConEsbirros(List<String> conEsbirros) {
        this.conEsbirros = conEsbirros;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }
}
