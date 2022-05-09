package modelos;

import controlador.Utilidades;

public class Armadura extends Equipo{


    @Override
    public Armadura modificar() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir(this.getNombre());
        this.setNombre(Utilidades.pedirCadena("Introduce nuevo nombre: "));
        Utilidades.imprimir(Integer.toString(this.getModAtq()));
        this.setModAtq(Utilidades.pedirEntero("Introduce nuevo valor: "));
        Utilidades.imprimir(Integer.toString(this.getModDef()));
        this.setModDef(Utilidades.pedirEntero("Introduce nuevo valor: "));
        return this;
    }
}
