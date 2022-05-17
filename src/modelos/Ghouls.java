package modelos;

public class Ghouls extends Esbirro{

    private int dependencia;
    private String descripcion;

    public Ghouls(String nombre, int salud, int dependencia, String descripcion) {
        super(nombre, salud);
        this.dependencia = dependencia;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDependencia() {
        return dependencia;
    }

    public void setDependencia(int dependencia) {
        this.dependencia = dependencia;
    }
}
