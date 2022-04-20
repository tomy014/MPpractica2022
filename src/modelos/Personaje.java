package modelos;

import java.util.List;
import controlador.Utilidades;

public interface Personaje {

    public void setNombre(String nombre);

    public void setArmas(List<Arma> armas);

    public void setArmaduras(List<Armadura> armaduras);

    public void setArmasActivas(List<Arma> armasActivas);

    public void setArmaduraActiva(Armadura armaduraActiva);

    public void setEsbirros(List<Esbirro> esbirros);

    public void setOro(int oro);

    public void setSalud(int salud);

    public void setPoder(int poder);

    public void setDebilidades(List<Debilidad> Debilidad);

    public void setFortalezas(List<Fortaleza> fortalezas);

    public void setHabilidad(String habilidad);

    public void setAtqHab(int ataque);

    public void setDefHab(int defensa);

    public void usarHabilidad();

    public void ganarRonda();

    public void perderRonda();

    public void calcularAtaque();

    public void calcularDefensa();


}
