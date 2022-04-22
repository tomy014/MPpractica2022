package modelos.factory;

import controlador.Utilidades;
import modelos.Personaje;
import modelos.Vampiro;

public class VampiroFactory extends PersonajeFactory{

    @Override
    public Personaje crearPersonaje() {
        Vampiro vampiro = new Vampiro();
        vampiro.setNombre(Utilidades.pedirCadena("Nombre: "));
        vampiro.setHabilidad(Utilidades.pedirCadena("Nombre de la disciplina: "));
        vampiro.setOro(500);
        vampiro.setSalud(5);
        vampiro.setAtqHab(Utilidades.pedirEntero("Ataque de la habilidad: "));
        vampiro.setDefHab(Utilidades.pedirEntero("Defensa de la habilidad: "));
        vampiro.setCosteHabilidad(Utilidades.pedirEntero("Coste de la habilidad: "));
        vampiro.setEdad(Utilidades.pedirEntero("Edad del vampiro: "));
        vampiro.setPoder(Utilidades.pedirEntero("Poder del personaje: "));
        vampiro.setPuntosSangre(Utilidades.pedirEntero("Puntos de sangre iniciales: "));

        return vampiro;
    }
}
