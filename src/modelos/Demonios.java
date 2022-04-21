package modelos;

import java.util.List;

public class Demonios extends Esbirro{

    private List<Esbirro> esbirros;

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
        for(int i = 0; i < this.esbirros.size(); i++){
            suma += this.esbirros.get(i).calcularSalud();
        }
        return suma;
    }
}
