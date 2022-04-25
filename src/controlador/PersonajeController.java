package controlador;

import modelos.Arma;
import modelos.Armadura;
import modelos.Personaje;
import modelos.Usuario;
import modelos.factory.CazadoresFactory;
import modelos.factory.LicantropoFactory;
import modelos.factory.VampiroFactory;

import java.util.List;

public class PersonajeController {

    /**
     * Recibe el personaje que quiero modificar armas.
     * @param pj personaje a modificar
     * @return personaje modificado.
     * @throws InterruptedException
     */
    public Personaje cambiarArmas(Personaje pj) throws InterruptedException {
        Utilidades.limpiarPantalla();
        List<Arma> armasActivas = pj.getArmasActivas();
        for (int i = 0; i < armasActivas.size(); i++) {
            Utilidades.imprimir("Arma actual: " + armasActivas.get(i).getNombre());
        }
        Utilidades.imprimir("");
        List<Arma> disponibles = pj.getArmas();
        for (int i = 0; i < disponibles.size(); i++) {
            Arma actual = disponibles.get(i);
            if(actual.isTipo())
                Utilidades.imprimir(i + ": "+ actual.getNombre() + ", de 2 manos");
            else
                Utilidades.imprimir(i + ": "+ actual.getNombre() + ", de 1 mano");
        }
        int num = Utilidades.pedirEntero("Elije nuevo arma, -1 para cancelar: ");
        if (num == -1)
            return pj;
        if (disponibles.get(num).isTipo()){
            armasActivas.clear();
            armasActivas.add(disponibles.get(num));
            pj.setArmasActivas(armasActivas);
        }
        else {
            Arma arma1 = disponibles.get(num);
            armasActivas.add(arma1);
            num = Utilidades.pedirEntero("Elije otra arma de 1 mano o -1 para terminar: ");
            if (num == -1)
                return pj;
            Arma arma2 = disponibles.get(num);
            if (arma2.isTipo() || arma1.equals(arma2))
                Utilidades.imprimir("Arma no válida, operación terminada.");
            else
                armasActivas.add(arma2);
            pj.setArmasActivas(armasActivas);
        }
        Utilidades.imprimir("Armas cambiadas. Volviendo...");
        Utilidades.pause(2);
        return pj;
    }

    public Personaje modificarArmadura(Personaje pj) throws InterruptedException {
        Utilidades.limpiarPantalla();
        Armadura armadura = pj.getArmaduraActiva();
        List<Armadura> disponibles = pj.getArmaduras();
        for (int i = 0; i < disponibles.size(); i++) {
            Armadura actual = disponibles.get(i);
            Utilidades.imprimir(i + ": " + actual.getNombre());
        }
        int num = Utilidades.pedirEntero("Elije nueva armadura, -1 para cancelar: ");
        if (num == -1)
            return pj;
        pj.setArmaduraActiva(disponibles.get(num));
        Utilidades.imprimir("Armadura cambiada. Volviendo...");
        Utilidades.pause(2);
        return pj;
    }

    /**
     * Modifica el usuario actual y registra un nuevo personaje, en caso de que no tenga uno ya.
     * @param u es el usuario en el que estamos..
     * @return Devuelve el usuario con su nuevo personaje ya añadido.
     * @throws InterruptedException
     */
    public Usuario registrarPersonaje(Usuario u) throws InterruptedException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("1. Vampiro");
        Utilidades.imprimir("2. Licántropo");
        Utilidades.imprimir("3. Cazador");
        Utilidades.imprimir("0. Cancelar.");
        int n = -1;
        while (n < 0 || n > 3) {
            n = Utilidades.pedirEntero("Selecciona una opción: ");
        }
        if (n == 0)
            return u;
        if (n == 1){
            VampiroFactory personaje = new VampiroFactory();
            u.setPj(personaje.crearPersonaje());
        } else if (n ==2) {
            LicantropoFactory personaje = new LicantropoFactory();
            u.setPj(personaje.crearPersonaje());
        } else if (n==3) {
            CazadoresFactory personaje = new CazadoresFactory();
            u.setPj(personaje.crearPersonaje());
        }
        else
            Utilidades.imprimir("Elección no válida.");
        Utilidades.imprimir("Personaje creado, volviendo...");
        Utilidades.pause(2);
        return u;
    }

}
