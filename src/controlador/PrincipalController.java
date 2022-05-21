package controlador;

import Observer.DesafiosObserver;
import modelos.Desafio;
import modelos.Operador;
import modelos.Personaje;
import modelos.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;

/**
 *     Controlador que inicia la aplicación.
 *     Se encarga de guardar todos los datos de la aplicación en ficheros.
 *     Cuando se arranca el programa los carga primero.
 */
public class PrincipalController {

    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private DesafioController desafioController = new DesafioController();


    /**carga todos los datos de usuarios, que son quien contienen a los personajes
     * y estos a su vez el resto de datos.
     * tmb carga historial de desafios.
     */
    public void cargarDatos() throws IOException, ClassNotFoundException {
        //this.usuarios = Utilidades.getListaUsuarios();
        //Operador de pruebas.

        //  OPERADOR PARA LAS PRUEBAS
        Operador operador = new Operador();
        operador.setNick("tomy");
        operador.setNombre("Alberto");
        operador.setBaneado(false);
        operador.setPassword("12345678");
        operador.setPj(null);
        operador.setNumReg("T07LG");
        operador.setOro(5000);

        this.usuarios = Utilidades.getListaUsuarios();
        //usuarios.add(operador);
        List<Desafio> listaDesafios;
        listaDesafios = Utilidades.getListaDesafios();
        //pedir a la lista desafios cargar sus datos y guardarlos.
        desafioController.cargarDatos(listaDesafios);
    }

    /**Guarda los datos, principalmente usuarios.
     *
     */
    public void guardarDatos() throws IOException {
        //recoge la lista de usuarios, y la guarda en fichero.
        Utilidades.setListaUsuarios(this.usuarios);
        //Utilidades.setListaDesafios(desafioController.guardarDatos());
    }

    /**
     * Método que siempre ejecuta la aplicación.
     * Lanza la pantalla de inicio.
     * @throws InterruptedException
     */
    public void lanzarAplicacion() throws InterruptedException, IOException, ClassNotFoundException {
        cargarDatos();//CARGA LOS DATOS QUE HAYA GUARDADOS.
        boolean continuar = true;
        while (continuar)
            try {
                continuar = pantallaLogin();
            }
            catch (IOException e){
                Utilidades.limpiarPantalla();
                e.printStackTrace();
                Utilidades.imprimir("Se ha producido un error en la aplicación, se va a reiniciar...");
                cargarDatos();
                Utilidades.pause(5);
                continuar=true;
            }
            catch (InputMismatchException e){
                Utilidades.limpiarPantalla();
                e.printStackTrace();
                Utilidades.imprimir("Se ha producido un error en la aplicación, error en el tipo de datos introducido.");
                Utilidades.imprimir("Se va a reiniciar la aplicación...");
                cargarDatos();
                Utilidades.pause(5);
                continuar=true;
            }
            catch (NullPointerException e){
                Utilidades.limpiarPantalla();
                e.printStackTrace();
                Utilidades.imprimir("Se ha producido un error en la aplicación, se va a reiniciar...");
                cargarDatos();
                Utilidades.pause(5);
                continuar=true;
            }
        guardarDatos();//GUARDA LOS DATOS
    }

    /**
     * Método que muestra las 2 opciones al comienzo del programa.
     * @throws InterruptedException
     */
    public boolean pantallaLogin() throws InterruptedException, IOException, ClassNotFoundException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("AVISO, puede ser necesario reiniciar la aplicación para aplciar cambios en su PJ.");
        Utilidades.imprimir("");
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
        guardarDatos();
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
        if (usu.getNick().equals("") || usu.getNombre().equals("") || usu.getPassword().equals("")){
            Utilidades.imprimir("Te ha faltado escribir algún dato, cancelando...");
            Utilidades.pause(2);
            return;
        }
        usu.rellenarDatos();
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
    public boolean iniciarSesion() throws InterruptedException, IOException, ClassNotFoundException {
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
                    //MIRAR SI ESTÁ BANEADO.
                    if (u.isBaneado()){
                        Utilidades.limpiarPantalla();
                        Utilidades.imprimir("Estás baneado, acceso denegado.");
                        Utilidades.imprimir("Contacte con un operador.");
                        Utilidades.pause(3);
                        return false;
                    }
                    //PANTALLA DESAFÍOS, COMPROBAR SI TIENE UN DESAFÍO ANTES DE NADA
                    DesafiosObserver observer = new DesafiosObserver();
                    observer.events.subscribe("desafio",new UsuarioController());
                    usuarios.remove(u);
                    Usuario usu = comprobarDesafios(u);
                    Usuario aux = buscarUsuario(usu.getNick());
                    if (aux==null)
                        usuarios.add(usu);
                    guardarDatos();
                    if (!usu.equals(u)){
                        Utilidades.imprimir("Se va a cerrar sesión por precaución, vuelva a entrar...");
                        Utilidades.pause(2);
                        break;
                    }
                    //usuarios.add(u);
                    usu = buscarUsuario(usu.getNick()); //actualizar usuario por si ha habido desafio y ha cambiado.
                    if (u.getClass().toString().equals("class modelos.Operador")) {
                        List<Usuario> modificados = usuarioController.menuOperador(usuarios, usu);
                        usuarios = modificados;
                        guardarDatos();
                    }
                    else {
                        Usuario modificado = usuarioController.menuUsuario(usu);
                        usuarios.remove(usu);
                        if (modificado!=null)
                            usuarios.add(modificado);
                    }
                    break;
                }
        }
        if (!encontrado){
            Utilidades.imprimir("Datos incorrectos, volviendo al menú...");
            Utilidades.pause(2);
            return false;
        }
        return true;
    }

    private Usuario comprobarDesafios(Usuario u) throws InterruptedException, IOException, ClassNotFoundException {
        desafioController.cargarDatos();
        List<Desafio> listaDesafios = desafioController.obtenerDesafios();
        for (Desafio d: listaDesafios) {
            if (d == null)
                continue;
            if(d.getDesafiado().getNick().equals(u.getNick())){
                if (d.isValidado() && d.getGanador()<0){//el desafio no se ha disputado aún
                    //TIENE DESAFIO
                    DesafiosObserver observer = new DesafiosObserver();
                    observer.notificarDesafio();
                    Utilidades.limpiarPantalla();
                    int respuesta = -1;
                    while (respuesta<0 || respuesta>1){
                        Utilidades.imprimir("Tienes un desafio de "+d.getDesafiante().getNick());
                        respuesta = Utilidades.pedirEntero("¿Quieres aceptar el desafio? 0 = NO // 1 = SI");
                    }
                    if (respuesta==0){
                        Desafio desafio = desafioController.rechazarDesafio(d);
                        u = desafio.getDesafiado();
                        Utilidades.imprimir("Tu nuevo oro es: "+u.getOro());
                        Usuario aux =buscarUsuario(d.getDesafiante().getNick());
                        usuarios.remove(aux);
                        usuarios.add(desafio.getDesafiante());
                        guardarDatos();
                        Usuario aux2 = buscarUsuario(u.getNick());
                        usuarios.remove(aux2);
                        usuarios.add(desafio.getDesafiado());
                    }
                    else {
                        //realizar desafio
                        u = buscarUsuario(u.getNick());
                        usuarios.remove(u);
                        usuarios.remove(d.getDesafiado());
                        Usuario atacante = buscarUsuario(d.getDesafiante().getNick());
                        usuarios.remove(atacante);
                        usuarios.remove(d.getDesafiante());
                        desafioController.removeDesafio(d);
                        desafioController.guardarDatos();
                        guardarDatos();
                        PersonajeController personajeController = new PersonajeController();
                        Personaje pj = u.getPj();
                        Utilidades.imprimir("Puedes cambiar tu equipamiento activo antes de empezar: ");
                        Utilidades.pause(2);
                        pj = personajeController.cambiarArmas(pj);
                        pj = personajeController.modificarArmadura(pj);
                        u.setPj(pj);
                        d.setDesafiado(u);
                        usuarios.remove(d.getDesafiado());
                        usuarios.remove(d.getDesafiante());
                        Desafio resultado = desafioController.iniciarDesafio(d);
                        u = resultado.getDesafiado();
                        Usuario aux =buscarUsuario(d.getDesafiante().getNick());
                        Usuario aux2 = buscarUsuario(u.getNick());
                        usuarios.remove(aux);
                        usuarios.remove(aux2);
                        usuarios.add(u);
                        usuarios.add(resultado.getDesafiante());
                        desafioController.anyadirDesafio(d);
                    }
                    guardarDatos();
                    desafioController.cargarDatos(listaDesafios);
                    observer.events.unsubscribe("desafio",new UsuarioController());
                    Utilidades.pause(2);
                }
            }
        }
        return u;
    }


    public Usuario buscarUsuario(String nick) throws IOException, ClassNotFoundException {
        cargarDatos(); //ESTA LINEA SE COMENTA SOLO PARA HACER LAS PRUEBAS, METODO TEST DA ERROR CON
        for (Usuario u : usuarios) {
            if (u==null)
                continue;
            if (u.getNick().equals(nick)){
                return u;
            }
        }
        return null;
    }


    public void mostrarRanking() throws InterruptedException, IOException, ClassNotFoundException {
        Utilidades.limpiarPantalla();
        cargarDatos();
        List<Usuario> aux = usuarios;
        aux.sort(Comparator.comparing(Usuario::getOro).reversed());

        Utilidades.imprimir("RANKING ACTUAL (desde último reinicio): ");
        for (int i = 0; i<aux.size() ;i++)
            Utilidades.imprimir(Integer.toString(i + 1) +".- "+ aux.get(i).getNick() +" "+ aux.get(i).getOro());
        Utilidades.pause(3);
        Utilidades.limpiarPantalla();
    }

    public void setUsuarios(List<Usuario> lista){
        this.usuarios=lista;
    }
}
