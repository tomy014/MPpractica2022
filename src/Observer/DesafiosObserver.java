package Observer;

import controlador.Utilidades;
import modelos.Usuario;

import java.io.File;

public class DesafiosObserver {

    public EventManager events;
    private Usuario usuario;

    public DesafiosObserver() {
        this.events = new EventManager("desafio");
    }

    public void notificarDesafio(){
        events.notify("desafio");
        Utilidades.imprimir("Tienes un desafío!!!");
    }
}
