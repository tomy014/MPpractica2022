package controlador;

import modelos.Operador;
import modelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class PrincipalController {

    private List<Usuario> usuarios = new ArrayList<Usuario>();
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
        //Operador de pruebas.
        Operador operador = new Operador();
        operador.setNick("tomy");
        operador.setNombre("Alberto");
        operador.setBaneado(false);
        operador.setPassword("12345678");
        operador.setPj(null);
        operador.setNumReg(null);
        usuarios.add(operador);

    }

    /**Guarda los datos, principalmente usuarios.
     *
     */
    public void guardarDatos(){

    }

    /**
     * Método que siempre ejecuta la aplicación.
     * Lanza la pantalla de inicio.
     * @throws InterruptedException
     */
    public void lanzarAplicacion() throws InterruptedException {
        cargarDatos();//CARGA LOS DATOS QUE HAYA GUARDADOS.
        boolean continuar = true;
        while (continuar)
            continuar = pantallaLogin();
        guardarDatos();//GUARDA LOS DATOS
    }

    /**
     * Método que muestra las 2 opciones al comienzo del programa.
     * @throws InterruptedException
     */
    public boolean pantallaLogin() throws InterruptedException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("1.- Iniciar sesión.");
        Utilidades.imprimir("2.- Registrarse.");
        Utilidades.imprimir("3.- Cerrar aplicación.");
        int num =-1;
        while (num!=1 && num!=2){
            num = Utilidades.pedirEntero("Elije una opción: ");
            if (num ==1)
                iniciarSesion();
            else if (num ==2)
                registrarse();
            else if (num == 3)
                return false;
            else
                Utilidades.imprimir("Opción incorrecta");
        }
        return true;
    }

    /**
     * Crea un nuevo usuario en caso de no existir.
     * Luego regresa al menú de login de nuevo.
     * @throws InterruptedException
     */
    public void registrarse() throws InterruptedException {
        Usuario usu = new Usuario();
        usu.setNick(Utilidades.pedirCadena("Nick: "));
        usu.setNombre(Utilidades.pedirCadena("Nombre: "));
        usu.setPassword(Utilidades.pedirCadena("Contraseña: "));
        boolean existe = false;
        if( usuarios.isEmpty()){
            usuarios.add(usu);
            Utilidades.imprimir("Usuario creado. Volviendo...");
            Utilidades.pause(2);
            return;
        }
        for ( Usuario aux : usuarios) {
            if (aux.getNick().equals(usu.getNick())){
                existe= true;
                break;
            }
        }
        if (existe){
            Utilidades.imprimir("Ya existe este usuario, volviendo...");
            Utilidades.pause(2);
        }
        else {
            usuarios.add(usu);
            Utilidades.imprimir("Usuario creado. Volviendo...");
            Utilidades.pause(2);
        }
    }

    /**
     * Pide datos de inicio, si son correctos lanza el menu de usuario hasta que finaliza
     * la sesión.
     * @throws InterruptedException
     */
    public void iniciarSesion() throws InterruptedException {
        String nombre = Utilidades.pedirCadena("Usuario: ");
        String pass = Utilidades.pedirCadena("Contraseña: ");
        boolean encontrado= false;
        for (Usuario u : usuarios) {
            if (u.getNick().equals(nombre))
                if (u.getPassword().equals(pass)){
                    encontrado=true;
                    UsuarioController usuarioController = new UsuarioController();
                    //PANTALLA DESAFÍOS, COMPROBAR SI TIENE UN DESAFÍO ANTES DE NADA
                    if (u.getClass().toString().equals("class modelos.Operador")){
                        List<Usuario> modificados = usuarioController.menuOperador(usuarios, u);
                        this.usuarios.clear();
                        usuarios = modificados;
                    }
                    else {
                        Usuario modificado = usuarioController.menuUsuario(u);
                        this.usuarios.remove(u);
                        this.usuarios.add(modificado);
                    }
                    break;
                }
        }
        if (!encontrado){
            Utilidades.imprimir("Datos incorrectos, volviendo al menú...");
            Utilidades.pause(2);
        }
    }


}
