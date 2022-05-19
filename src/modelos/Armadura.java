package modelos;

import controlador.Utilidades;

import java.io.Serializable;

public class Armadura extends Equipo implements Serializable {


    @Override
    public Armadura modificar() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Nombre: "+this.getNombre());
        this.setNombre(Utilidades.pedirCadena("Introduce nuevo nombre: "));
        while (this.getNombre().equals(""))
            this.setNombre(Utilidades.pedirCadena("No puede estar vacío: "));
        Utilidades.imprimir(("Modificador de ataque: "+this.getModAtq()));
        this.setModAtq(Utilidades.pedirEntero("Introduce nuevo valor: "));
        Utilidades.imprimir("Modificador de defensa: "+this.getModDef());
        this.setModDef(Utilidades.pedirEntero("Introduce nuevo valor: "));
        return this;
    }
}
