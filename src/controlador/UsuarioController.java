package controlador;

import modelos.Usuario;

public class UsuarioController {

    //Podría tener una lista de todos los usuarios de la aplicación, y devolverla al principal para guardarlos
    //y así hacer que sea persistente.


    public void darseBaja(){

    }

    public void desafiarUsuario(){

    }

    /**
     * Mostrar en pantalla todos los combates de la aplicación.
     */
    public void consultarOro(){

    }

    public void rechazarDesafio(){

    }

    public void acerptarDesafio(){

    }

    public void verRanking(){

    }

    public void bajaPersonaje(Usuario usuario) throws InterruptedException {
        PersonajeController pjController = new PersonajeController();
        if (pjController.bajaPersonaje())
            usuario.setPj(null);
        Utilidades.imprimir("Personaje eliminado.");
        Utilidades.pause(3);

    }
}
