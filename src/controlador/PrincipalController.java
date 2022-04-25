package controlador;

import modelos.Usuario;

import java.util.List;

public class PrincipalController {

    private List<Usuario> usuarios;
    /*
    Controlador que inicia la aplicación.
    Se encarga de guardar todos los datos de la aplicación en ficheros.
    Cuando se arranca el programa los carga primero.

     */

    /**carga todos los datos de usuarios, que son quien contienen a los personajes
     * y estos a su vez el resto de datos.
     * tmb carga historial de desafios.
     */
    public void cargarDatos(){

    }

    /**Guarda los datos, principalmente usuarios.
     *
     */
    public void guardarDatos(){

    }

    /**
     * Método que siempre ejecuta la aplicación.
     * @throws InterruptedException
     */
    public void lanzarAplicacion() throws InterruptedException {
        while (true)
            menuLogin();
    }

    /**
     * Método que muestra las 2 opciones al comienzo del programa.
     * @throws InterruptedException
     */
    public void menuLogin() throws InterruptedException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("1.- Iniciar sesión.");
        Utilidades.imprimir("2.- Registrarse.");
        int num =-1;
        while (num!=1 || num!=2){
            num = Utilidades.pedirEntero("Elije una opción: ");
            if (num ==1)
                iniciarSesion();
            else if (num ==2)
                registrarse();
            else
                Utilidades.imprimir("Opción incorrecta");
        }
    }

    public void registrarse() {

    }

    public void iniciarSesion() throws InterruptedException {
        String nombre = Utilidades.pedirCadena("Usuario: ");
        String pass = Utilidades.pedirCadena("Contraseña: ");
        boolean encontrado= true;
        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre))
                if (u.getPassword().equals(pass)){
                    encontrado=true;
                    //Llamamos al método logear este usuario hasta que finalice la sesión.
                    break;
                }
        }
        if (!encontrado)
            Utilidades.imprimir("Datos incorrectos, volviendo al menú.");
        Utilidades.pause(2);
    }
}
