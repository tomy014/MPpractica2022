package modelos;

public class Arma extends Equipo{

    private boolean tipo;//true = 2 manos, false = 1 mano

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }
}
