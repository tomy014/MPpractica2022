package modelos.factory;

import controlador.Utilidades;
import modelos.Cazador;

public class CazadoresFactory extends PersonajeFactory{

    @Override
    public Cazador crearPersonaje() {
        Cazador cazador = new Cazador();
        cazador.setNombre(Utilidades.pedirCadena("Nombre: "));
        cazador.setHabilidad(Utilidades.pedirCadena("Nombre del talento: "));
        cazador.setAtqHab(Utilidades.pedirEntero("Ataque de la habilidad: "));
        cazador.setDefHab(Utilidades.pedirEntero("Defensa de la habilidad: "));
        cazador.setPoder(Utilidades.pedirEntero("Poder del personaje: "));
        cazador.setOro(500);
        cazador.setSalud(5);
        cazador.setVoluntad(3);//Siempre empieza en 3

        return cazador;
    }
}
