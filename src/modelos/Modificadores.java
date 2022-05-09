package modelos;

import controlador.Utilidades;

public abstract class Modificadores {

    private String nombre;
    private int valor;
    private boolean activo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Modificadores modificar() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir(this.getNombre());
        this.setNombre(Utilidades.pedirCadena("Introduce nuevo nombre: "));
        Utilidades.imprimir(Integer.toString(this.getValor()));
        this.setValor(Utilidades.pedirEntero("Introduce nuevo valor: "));
        this.setActivo(false);
        return this;
    }
}
