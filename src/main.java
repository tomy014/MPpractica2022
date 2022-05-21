import controlador.PrincipalController;
import controlador.Utilidades;
import org.junit.jupiter.api.Test;

import java.io.IOException;

//Inicio de la applicación.
public class main {
    @Test

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        //cargar todos los datos antes de ejecutar el programa
        //llamada a controlador de la aplicación.
        PrincipalController principal = new PrincipalController();
        principal.lanzarAplicacion();
    }
}
