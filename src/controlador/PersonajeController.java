package controlador;

import modelos.Arma;
import modelos.Armadura;
import modelos.Personaje;

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

    public boolean bajaPersonaje(){
        Utilidades.limpiarPantalla();
        int num = Utilidades.pedirEntero("¿Estás seguro? Pulsa 1 para confirmar, otro para cancelar");
        if (num == 1)
            return true;
        else
            return false;
    }


}
