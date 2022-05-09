package modelos;

import java.util.List;

import controlador.PersonajeController;
import controlador.Utilidades;

public interface Personaje {
    
    PersonajeController controller = new PersonajeController();

    public void setNombre(String nombre);

    public void setArmas(List<Arma> armas);

    public List<Arma> getArmas();

    public void setArmaduras(List<Armadura> armaduras);

    public List<Armadura> getArmaduras();

    public void setArmasActivas(List<Arma> armasActivas);

    public List<Arma> getArmasActivas();

    public void setArmaduraActiva(Armadura armaduraActiva);

    public Armadura getArmaduraActiva();

    public void setEsbirros(List<Esbirro> esbirros);

    public List<Esbirro> getEsbirros();

    public void setOro(int oro);

    public void setSalud(int salud);

    public void setPoder(int poder);

    public void setDebilidades(List<Debilidad> Debilidad);

    public List<Debilidad> getDebilidades();

    public void setFortalezas(List<Fortaleza> fortalezas);

    public List<Fortaleza> getFortalezas();

    public void setHabilidad(String habilidad);

    public void setAtqHab(int ataque);

    public void setDefHab(int defensa);

    public void usarHabilidad();

    public void ganarRonda();

    public void perderRonda();

    public int calcularAtaque();

    public int calcularDefensa();


    public void modificarDatos();

    public Esbirro crearEsbirros();

}
