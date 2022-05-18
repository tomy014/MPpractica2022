import controlador.PrincipalController;
import controlador.Utilidades;

import java.io.IOException;

//Inicio de la applicación.
public class main {

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        //cargar todos los datos antes de ejecutar el programa
        //llamada a controlador de la aplicación.
        PrincipalController principal = new PrincipalController();
        principal.lanzarAplicacion();
    }
}
