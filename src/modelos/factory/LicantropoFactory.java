package modelos.factory;

import controlador.Utilidades;
import modelos.Licantropo;

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

        return licantropo;
    }
}
