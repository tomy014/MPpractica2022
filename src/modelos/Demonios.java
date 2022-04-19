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
}
