package modelos;

import controlador.Utilidades;

import java.io.Serializable;

public class Armadura extends Equipo implements Serializable {


    @Override
    public Armadura modificar() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir(this.getNombre());
        this.setNombre(Utilidades.pedirCadena("Introduce nuevo nombre: "));
        while (this.getNombre().equals(""))
            this.setNombre(Utilidades.pedirCadena("No puede estar vacío: "));
        Utilidades.imprimir(Integer.toString(this.getModAtq()));
        this.setModAtq(Utilidades.pedirEntero("Introduce nuevo valor: "));
        Utilidades.imprimir(Integer.toString(this.getModDef()));
        this.setModDef(Utilidades.pedirEntero("Introduce nuevo valor: "));
        return this;
    }
}
