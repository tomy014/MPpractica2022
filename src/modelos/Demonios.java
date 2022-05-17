package modelos;

import java.util.List;

public class Demonios extends Esbirro{
    //tiene que usar el patrón composite.

    private List<Esbirro> esbirros;

    public Demonios(String nombre, int salud, List<Esbirro> esbirros) {
        super(nombre, salud);
        this.esbirros = esbirros;
    }

    public List<Esbirro> getEsbirros() {
        return esbirros;
    }

    public void setEsbirros(List<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }

    public void addEsbirro(Esbirro esbirro) {
        this.esbirros.add(esbirro);
    }

    //Calcula la salud del esbirro, y luego le va sumando la salud de sus otros esbirros.
    @Override
    public int calcularSalud() {
        int suma =getSalud();
        if (this.esbirros==null)
            return suma;
        for(int i = 0; i < this.esbirros.size(); i++){
            if (this.esbirros==null)
                continue;
            if (this.esbirros.get(i)==null)
                continue;
            suma += this.esbirros.get(i).calcularSalud();
        }
        return suma;
    }
}
