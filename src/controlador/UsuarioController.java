package controlador;

import Observer.DesafiosObserver;
import Observer.EventListener;
import modelos.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class UsuarioController implements EventListener {

    @Override
    public void notificar() {

    }

    public boolean darseBaja() throws InterruptedException {
        Utilidades.limpiarPantalla();
        int num = Utilidades.pedirEntero("¿Estás seguro? Pulsa 1 para confirmar, otro para cancelar");
        if (num == 1){
            Utilidades.imprimir("Usuario eliminado, volviendo...");
            Utilidades.pause(2);
            return true;
        }
        return false;
    }

    public void verRanking() throws InterruptedException, IOException, ClassNotFoundException {
        PrincipalController auxController = new PrincipalController();
        auxController.mostrarRanking();
    }

    private void verDesafios() throws InterruptedException, IOException, ClassNotFoundException {
        DesafioController desafioController = new DesafioController();
        desafioController.mostrarDesafios();
    }

    private void verCombates(Usuario u) throws InterruptedException, IOException, ClassNotFoundException {
        DesafioController desafioController = new DesafioController();
        desafioController.mostrarDesafios(u);
    }

    public boolean bajaPersonaje(Personaje pj) throws InterruptedException {
        Utilidades.limpiarPantalla();
        if(pj == null){
            Utilidades.imprimir("No tienes ningún personaje, volviendo...");
            Utilidades.pause(2);
            return false;
        }
        int num = Utilidades.pedirEntero("¿Estás seguro? Pulsa 1 para confirmar, otro para cancelar");
        if (num == 1){
            Utilidades.imprimir("Personaje eliminado.");
            Utilidades.pause(2);
            return true;
        }
        return false;
    }

    public Usuario menuUsuario(Usuario usuario) throws InterruptedException, IOException, ClassNotFoundException {
        boolean continuar = true;
        Utilidades.limpiarPantalla();
        while (continuar){
            int n = 0;
            while (n==0){
                Utilidades.imprimir("1. Crear personaje");
                Utilidades.imprimir("2. Dar de baja al personaje");
                Utilidades.imprimir("3. Cambiar de equipo activo");
                Utilidades.imprimir("4. Lanzar desafío");
                Utilidades.imprimir("5. Resultados de combates");
                Utilidades.imprimir("6. Ver ranking");
                Utilidades.imprimir("7. Darse de baja");
                Utilidades.imprimir("8. Cerrar sesión");
                n = Utilidades.pedirEntero("Elije una opción");
                switch (n){
                    case 1:
                        Personaje mod = usuario.getPj();
                        if (mod == null){
                            PersonajeController controller = new PersonajeController();
                            usuario = controller.registrarPersonaje(usuario);
                        }
                        else {
                            Utilidades.imprimir("Ya tienes un personaje.");
                            Utilidades.pause(2);
                            break;
                        }
                        break;
                    case 2:
                        mod = usuario.getPj();
                        if (bajaPersonaje(mod))
                            usuario.setPj(null);
                        break;
                    case 3:
                        mod = usuario.getPj();
                        if (mod == null){
                            Utilidades.imprimir("No tienes ningún personaje.");
                            Utilidades.pause(2);
                            break;
                        }
                        Utilidades.limpiarPantalla();
                        Utilidades.imprimir("1. Cambiar armas activas.");
                        Utilidades.imprimir("2. Cambiar armadura activa.");
                        Utilidades.imprimir("Otro. Cancelar.");
                        int o =Utilidades.pedirEntero("Eljie una opción: ");
                        if (o==1){
                            if (mod.getArmas()==null) {
                                Utilidades.imprimir("Tu personaje no tiene armas disponibles.");
                                Utilidades.pause(2);
                                break;
                            }
                            PersonajeController controller = new PersonajeController();
                            mod = controller.cambiarArmas(mod);
                            usuario.setPj(mod);
                        } else if (o==2){
                            if (mod.getArmaduras()==null) {
                                Utilidades.imprimir("Tu personaje no tiene armaduras disponibles.");
                                Utilidades.pause(2);
                                break;
                            }
                            PersonajeController controller = new PersonajeController();
                            mod = controller.modificarArmadura(mod);
                            usuario.setPj(mod);
                        }
                        break;
                    case 4:
                        //Mandar desafio
                        lanzarDesafio(usuario);
                        break;
                    case 5:
                        //Historial de combates
                        verCombates(usuario);
                        break;
                    case 6:
                        //Ver Ranking
                        verRanking();
                        break;
                    case 7:
                        if(darseBaja())
                            return null;
                        else
                            break;
                    case 8:
                        continuar = false;
                        break;
                    default:
                        Utilidades.imprimir("Opción incorrecta.");
                        Utilidades.pause(1);
                        break;
                }
            }
        }
        return usuario;
    }

    public List<Usuario> menuOperador(List<Usuario> usuarios, Usuario usu) throws InterruptedException, IOException, ClassNotFoundException {
        boolean continuar = true;
        Utilidades.limpiarPantalla();
        while (continuar){
            int n = 0;
            while (n==0){
                Utilidades.imprimir("1. Editar personaje");
                Utilidades.imprimir("2. Validar desafíos");
                Utilidades.imprimir("3. Banear usuarios");
                Utilidades.imprimir("4. Desbanear usuarios");
                Utilidades.imprimir("5. Resultados de combates");
                Utilidades.imprimir("6. Darse de baja");
                Utilidades.imprimir("7. Cerrar sesión");
                n = Utilidades.pedirEntero("Elije una opción");
                switch (n){
                    case 1:
                        //Editar personajes.
                        Usuario u = elegirUsuario(usuarios, "Escoge usuario a modificar su personaje: ");
                        if (u==null){
                            break;
                        }
                        if (u.getPj()== null){
                            Utilidades.imprimir("El usuario no tiene personaje, volviendo...");
                            Utilidades.pause(2);
                            break;
                        }
                        PersonajeController pjController = new PersonajeController();
                        usuarios.remove(u);
                        u.setPj(pjController.modificarPersonaje(u.getPj()));
                        usuarios.add(u);
                        break;
                    case 2:
                        //Validar desafíos pendientes.
                        verDesafios();//Pone la lista de desafios en pantalla
                        int op = Utilidades.pedirEntero("Selecciona desafio o -1 cancelar: ");
                        DesafioController desafioController = new DesafioController();
                        boolean validado=desafioController.validarDesafio(op);
                        if (validado){
                            //añadir este usuario a la lista de suscriptores de notificacion desafio
                            DesafiosObserver ob= new DesafiosObserver();
                            ob.notificarDesafio();

                        }
                        break;
                    case 3:
                        //Banear usuarios
                        u = elegirUsuario(usuarios, "Escoge usuario a banear: ");
                        if (u==null){
                            break;
                        }
                        if (!u.isBaneado()){
                            usuarios.remove(u);
                            u.setBaneado(true);
                            usuarios.add(u);
                            Utilidades.imprimir("Usuario baneado, volviendo...");
                        }
                        else {
                            Utilidades.imprimir("Error, el usuario ya está baneado. Volviendo...");
                        }
                        Utilidades.pause(2);
                        break;
                    case 4:
                        //Desbanear usuarios
                        u = elegirUsuario(usuarios, "Escoge usuario a desbanear: ");
                        if (u==null){
                            break;
                        }
                        if (u.isBaneado()){
                            usuarios.remove(u);
                            u.setBaneado(false);
                            Utilidades.imprimir("Usuario desbaneado, volviendo...");
                            usuarios.add(u);
                        }
                        else {
                            Utilidades.imprimir("Error, el usuario ya está desbaneado. Volviendo...");
                        }
                        Utilidades.pause(2);
                        break;
                    case 5:
                        //Ver historial de desafíos
                        verDesafios();

                        break;
                    case 6:
                        if(darseBaja()){
                            usuarios.remove(usu);
                            return usuarios;
                        }
                        else
                            break;
                    case 7:
                        continuar = false;
                        break;
                    default:
                        Utilidades.imprimir("Opción incorrecta.");
                        Utilidades.pause(1);
                        break;
                }
            }
        }
        return usuarios;
    }

    public Usuario elegirUsuario(List<Usuario> usuarios, String motivo) throws InterruptedException {
        int t = usuarios.size();
        if (usuarios==null || t==0){
            Utilidades.imprimir("No hay usuarios registrados, volviendo...");
            Utilidades.pause(2);
            return null;
        }
        Utilidades.limpiarPantalla();
        for (int i = 0; i < t; i++) {
            if (usuarios.get(i)==null)
                continue;
            Utilidades.imprimir(i +". "+usuarios.get(i).getNick());
        }
        //t++;
        Utilidades.imprimir("Otro para cancelar operación.");
        int n = -1;
        n = Utilidades.pedirEntero(motivo);
        if (n>=t || n<0){
            return null;
        }
        return usuarios.get(n);
    }

    public boolean lanzarDesafio(Usuario usuario) throws InterruptedException, IOException, ClassNotFoundException {
        Utilidades.limpiarPantalla();
        String nick = Utilidades.pedirCadena("Usuario a desafiar (Nick): ");
        if (nick.equals(usuario.getNick())){
            Utilidades.imprimir("No te puedes desafiar a ti mismo, volviendo...");
            Utilidades.pause(2);
            return false;
        }
        PrincipalController auxController = new PrincipalController();
        Usuario rival = auxController.buscarUsuario(nick);
        if (rival==null){
            Utilidades.imprimir("No existe dicho usuario, volviendo...");
            Utilidades.pause(2);
            return false;
        }

        int oro = usuario.getPj().getOro();
        if (oro<=0){
            Utilidades.imprimir("No tienes suficiente oro, volviendo...");
            Utilidades.pause(2);
            return false;
        }
        int apuesta =Utilidades.pedirEntero("Oro a apostar, negativo o cero para cancelar: ");
        if (apuesta<=0)
            return false;
        if (apuesta>oro){
            Utilidades.imprimir("No tienes ese oro disponible, volviendo...");
            Utilidades.pause(2);
            return false;
        }
        //Crear desafío
        DesafioController desafioController = new DesafioController();
        Desafio desafio = new Desafio();
        desafio.setDesafiado(rival);
        desafio.setDesafiante(usuario);
        desafio.setFecha(LocalDate.now());
        desafio.setOroApostado(oro);
        desafio.setGanador(-1);
        desafio.setOroGanado(-1);
        desafio.setValidado(false);
        desafioController.anyadirDesafio(desafio);
        Utilidades.imprimir("Desafío guardado, pendiente de aprobación, volviendo...");
        Utilidades.pause(2);
        return true;
    }
}
