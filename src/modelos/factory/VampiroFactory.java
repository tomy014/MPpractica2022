package modelos.factory;

import controlador.Utilidades;
import modelos.Arma;
import modelos.Armadura;
import modelos.Vampiro;

import java.util.ArrayList;

public class VampiroFactory extends PersonajeFactory{

    @Override
    public Vampiro crearPersonaje() {
        Vampiro vampiro = new Vampiro();
        vampiro.setNombre(Utilidades.pedirCadena("Nombre: "));
        vampiro.setHabilidad(Utilidades.pedirCadena("Nombre de la disciplina: "));
        vampiro.setAtqHab(Utilidades.pedirEntero("Ataque de la habilidad: "));
        vampiro.setDefHab(Utilidades.pedirEntero("Defensa de la habilidad: "));
        vampiro.setCosteHabilidad(Utilidades.pedirEntero("Coste de la habilidad: "));
        vampiro.setPoder(Utilidades.pedirEntero("Poder del personaje: "));
        vampiro.setOro(500);
        vampiro.setSalud(5);
        vampiro.setEdad(Utilidades.pedirEntero("Edad del vampiro: "));
        vampiro.setPuntosSangre(Utilidades.pedirEntero("Puntos de sangre iniciales: "));

        vampiro.setArmaduras(new ArrayList<Armadura>());
        vampiro.setArmas(new ArrayList<Arma>());
        vampiro.setArmasActivas(new ArrayList<Arma>());
        vampiro.setArmaduraActiva(new Armadura());

        return vampiro;
    }
}
