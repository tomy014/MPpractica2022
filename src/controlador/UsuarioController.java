package controlador;

import modelos.Operador;
import modelos.Personaje;
import modelos.Usuario;

public class UsuarioController {

    //Podría tener una lista de todos los usuarios de la aplicación, y devolverla al principal para guardarlos
    //y así hacer que sea persistente.


    public boolean darseBaja() throws InterruptedException {
        Utilidades.limpiarPantalla();
        int num = Utilidades.pedirEntero("¿Estás seguro? Pulsa 1 para confirmar, otro para cancelar");
        if (num == 1){
            Utilidades.imprimir("Personaje eliminado.");
            Utilidades.pause(2);
            return true;
        }
        return false;
    }

    public void desafiarUsuario(){

    }

    /**
     * Mostrar en pantalla todos los combates de la aplicación.
     */
    public void consultarOro(){

    }

    public void rechazarDesafio(){

    }

    public void acerptarDesafio(){

    }

    public void verRanking(){

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

    public Usuario menuUsuario(Usuario usuario) throws InterruptedException {
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
                        break;
                    case 5:
                        //Historial de combates
                        break;
                    case 6:
                        //
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

    public Operador menuOperador(Usuario u) {
        return null;
    }


}
