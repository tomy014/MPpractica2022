package modelos.factory;

import controlador.Utilidades;
import modelos.Arma;
import modelos.Armadura;
import modelos.Licantropo;

import java.util.ArrayList;

public class LicantropoFactory extends PersonajeFactory{

    @Override
    public Licantropo crearPersonaje() {
        Licantropo licantropo = new Licantropo();
        licantropo.setNombre(Utilidades.pedirCadena("Nombre: "));
        licantropo.setHabilidad(Utilidades.pedirCadena("Nombre del don: "));
        licantropo.setAtqHab(Utilidades.pedirEntero("Ataque de la habilidad: "));
        licantropo.setDefHab(Utilidades.pedirEntero("Defensa de la habilidad: "));
        licantropo.setCosteHabilidad(Utilidades.pedirEntero("Coste de la habilidad: "));
        licantropo.setPoder(Utilidades.pedirEntero("Poder del personaje: "));
        licantropo.setOro(500);
        licantropo.setSalud(5);
        licantropo.setRabia(Utilidades.pedirEntero("Rabia inicial: "));

        licantropo.setArmaduras(new ArrayList<Armadura>());
        licantropo.setArmas(new ArrayList<Arma>());
        licantropo.setArmasActivas(new ArrayList<Arma>());
        licantropo.setArmaduraActiva(new Armadura());

        return licantropo;
    }
}
