package controlador;

import Observer.DesafiosObserver;
import modelos.Desafio;
import modelos.Operador;
import modelos.Personaje;
import modelos.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *     Controlador que inicia la aplicación.
 *     Se encarga de guardar todos los datos de la aplicación en ficheros.
 *     Cuando se arranca el programa los carga primero.
 */
public class PrincipalController {

    private List<Usuario> usuarios = new ArrayList<Usuario>();


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
        operador.setOro(5000);
        usuarios.add(operador);
        ArrayList<Desafio> listaDesafios = new ArrayList<Desafio>();
        //pedir a la lista desafios cargar sus datos y guardarlos.
        //listaDesafios = (coger del fichero)
        DesafioController auxController = new DesafioController();
        auxController.cargarDatos(listaDesafios);
    }

    /**Guarda los datos, principalmente usuarios.
     *
     */
    public void guardarDatos(){
        //recoge la lista de usuarios, y la guarda en fichero.
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
            if (aux==null)
                continue;
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
            if (u == null)
                continue;
            if (u.getNick().equals(nombre))
                if (u.getPassword().equals(pass)){
                    encontrado=true;
                    UsuarioController usuarioController = new UsuarioController();
                    //PANTALLA DESAFÍOS, COMPROBAR SI TIENE UN DESAFÍO ANTES DE NADA
                    DesafiosObserver observer = new DesafiosObserver();
                    observer.events.subscribe("desafio",new UsuarioController());
                    comprobarDesafios(u);
                    u = buscarUsuario(u.getNick());//actualizar usuario por si ha habido desafio y ha cambiado.
                    if (u.getClass().toString().equals("class modelos.Operador")) {
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

    private void comprobarDesafios(Usuario u) throws InterruptedException {
        DesafioController auxController = new DesafioController();
        List<Desafio> listaDesafios = auxController.obtenerDesafios();
        for (Desafio d: listaDesafios) {
            if (d == null)
                continue;
            if(d.getDesafiado().getNick().equals(u.getNick())){
                if (d.isValidado() && d.getGanador()<0){//el desafio no se ha disputado aún
                    //TIENE DESAFIO
                    Utilidades.limpiarPantalla();
                    int respuesta = -1;
                    while (respuesta<0 && respuesta>1){
                        Utilidades.imprimir("Tienes un desafio de "+d.getDesafiante().getNick());
                        respuesta = Utilidades.pedirEntero("¿Quieres aceptar el desafio? 0 = NO // 1 = SI");
                    }
                    if (respuesta==0){
                        Desafio desafio = auxController.rechazarDesafio(d);
                        usuarios.remove(u);
                        u = desafio.getDesafiado();
                        Utilidades.imprimir("Tu nuevo oro es: "+u.getOro());
                        Usuario aux =buscarUsuario(d.getDesafiante().getNick());
                        usuarios.remove(aux);
                        usuarios.add(desafio.getDesafiante());
                        usuarios.add(u);
                        return;
                    }
                    else {
                        //realizar desafio
                        usuarios.remove(u);
                        PersonajeController personajeController = new PersonajeController();
                        Personaje pj = u.getPj();
                        pj = personajeController.cambiarArmas(pj);
                        pj = personajeController.modificarArmadura(pj);
                        u.setPj(pj);
                        auxController.removeDesafio(d);
                        d.setDesafiado(u);
                        d = auxController.iniciarDesafio(d);
                        u = d.getDesafiado();
                        Usuario aux =buscarUsuario(d.getDesafiante().getNick());
                        usuarios.remove(aux);
                        usuarios.add(d.getDesafiante());
                        usuarios.add(u);
                        return;
                    }
                }
            }
        }
    }


    public Usuario buscarUsuario(String nick){
        cargarDatos();
        for (Usuario u : usuarios) {
            if (u==null)
                continue;
            if (u.getNick().equals(nick)){
                return u;
            }
        }
        return null;
    }


    public void mostrarRanking() throws InterruptedException {
        Utilidades.limpiarPantalla();
        cargarDatos();
        List<Usuario> aux = usuarios;
        aux.sort(Comparator.comparing(Usuario::getOro));
        Utilidades.imprimir("RANKING ACTUAL (desde último reinicio): ");
        for (int i = 0; i<aux.size() ;i++)
            Utilidades.imprimir(Integer.toString(i + 1) +".- "+ aux.get(i).getNick() +" "+ aux.get(i).getOro());
        Utilidades.pause(3);
        Utilidades.limpiarPantalla();
    }
}
