package modelos;

import controlador.Utilidades;

public class Arma extends Equipo{

    private boolean tipo;//true = 2 manos, false = 1 mano

    /**
     * @return true si es de 2 manos, false si es de 1 mano.
     */
    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }


    @Override
    public Arma modificar() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir(this.getNombre());
        this.setNombre(Utilidades.pedirCadena("Introduce nuevo nombre: "));
        Utilidades.imprimir(Integer.toString(this.getModAtq()));
        this.setModAtq(Utilidades.pedirEntero("Introduce nuevo valor: "));
        Utilidades.imprimir(Integer.toString(this.getModDef()));
        this.setModDef(Utilidades.pedirEntero("Introduce nuevo valor: "));
        if (isTipo())
            if(Utilidades.pedirEntero("Es de 2 manos, pulse 1 para cambiar a 1 mano.") == 1)
                setTipo(false);
        else
            if(Utilidades.pedirEntero("Es de 1 mano, pulse 1 para cambiar a 2 manos.") == 1)
                setTipo(true);
        return this;
    }
}
