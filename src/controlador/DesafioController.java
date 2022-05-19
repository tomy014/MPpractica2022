package controlador;

import modelos.Personaje;
import modelos.Desafio;
import modelos.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

public class DesafioController {

    private List<Desafio> desafios = new ArrayList<Desafio>();

    /**
     * inicia el controlador, obteniendo la lista y guardandola en el controlador.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void cargarDatos() throws IOException, ClassNotFoundException {
        this.desafios = Utilidades.getListaDesafios();
    }

    /**
     * recibe una lista, y la guarda en el fichero.
     * @param listaDesafios
     * @throws IOException
     */
    public void cargarDatos(List<Desafio> listaDesafios) throws IOException {
        Utilidades.setListaDesafios(listaDesafios);
        this.desafios = listaDesafios;
    }

    /**
     * pide al controlador que guarde la lista que tiene en el fichero.
     * @return lista de desafios acutales
     * @throws IOException
     */
    public List<Desafio> guardarDatos() throws IOException {
        Utilidades.setListaDesafios(desafios);
        return desafios;
    }

    /**
     * actualiza lo existente
     * @param desafio
     * @throws IOException
     */
    public void anyadirDesafio(Desafio desafio) throws IOException, ClassNotFoundException {
        cargarDatos(); //refresca cambios
        this.desafios.add(desafio); //añade nuevo desafío al fichero
        guardarDatos();
    }

    public List<Desafio> obtenerDesafios() {
        //hace get de la lista desafíos
        return this.desafios;
    }

    public void notificar(Usuario u) throws InterruptedException, IOException, ClassNotFoundException {
        //decir que el usuario tiene desafio, ejecuta si lo realiza o no.
        for (Desafio d: desafios) {
            if (d == null)
                continue;
            if(d.getDesafiado().getNick().equals(u.getNick())){
                if (d.isValidado() && d.getGanador()<0){//el desafio no se ha disputado aún
                    //TIENE DESAFIO
                    Utilidades.limpiarPantalla();
                    int respuesta = -1;
                    while (respuesta<0 &&respuesta>1){
                        Utilidades.imprimir("Tienes un desafio de "+d.getDesafiante().getNick());
                        respuesta = Utilidades.pedirEntero("¿Quieres aceptar el desafio? 0 = NO // 1 = SI");
                    }
                    if (respuesta==0){
                        Desafio desafio = this.rechazarDesafio(d);
                        //modificar la lista de desafíos con los cambios
                        return;
                    }
                    else {
                        //realizar desafio
                        Desafio desafio = iniciarDesafio(d);
                    }
                }
            }
        }
    }

    public Desafio iniciarDesafio(Desafio d) throws InterruptedException {
        Personaje atacante = d.getDesafiante().getPj();
        Personaje desafiado = d.getDesafiado().getPj();
        int saludAtacante = atacante.getSalud();
        int saludDesafiado = desafiado.getSalud();
        int saludEsbirrosAtq= atacante.saludEsbirros();
        int saludEsbirrosDes = desafiado.saludEsbirros();
        saludAtacante+=saludEsbirrosAtq;
        saludDesafiado+=saludEsbirrosDes;
        int rondas=0;
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Comienza el combate....");
        Utilidades.imprimir("");
        Utilidades.pause(1);
        while (saludAtacante>0 && saludDesafiado>0){
            //ejecución de una ronda hasta que alguien se queda sin salud

            //primero ataca el que lanzó el desafío.
            Utilidades.imprimir("Turno atacante.");
            int atqAtacante = atacante.calcularAtaque();
            atqAtacante=totalAtaque(atqAtacante);
            int defDesafiado = desafiado.calcularDefensa();
            defDesafiado=totalDefensa(defDesafiado);
            if (atqAtacante>=defDesafiado){
                desafiado.perderRonda();
                atacante.ganarRonda();
                saludDesafiado--;
            }
            else
                Utilidades.imprimir("Ataque fallido.");
            //ahora el desafiado prueba a defenderse.
            Utilidades.imprimir("");
            Utilidades.imprimir("Turno defensor.");
            int atqDesafiado = desafiado.calcularAtaque();
            atqDesafiado=totalAtaque(atqDesafiado);
            int defAtacante = atacante.calcularDefensa();
            defAtacante=totalDefensa(defAtacante);
            if (atqDesafiado>=defAtacante){
                desafiado.ganarRonda();
                atacante.perderRonda();
                saludAtacante--;
            }
            else
                Utilidades.imprimir("Ataque fallido.");
            Utilidades.imprimir("");
            Utilidades.imprimir("Fin ronda.");
            Utilidades.pause(3);
            Utilidades.imprimir("");
            rondas++;
        }
        //alguien se ha quedado sin salud
        d.getDesafiante().setPj(atacante);
        d.getDesafiado().setPj(desafiado);
        if (saludAtacante<=0 && saludDesafiado<=0){
            d.setGanador(0);//EMPATE
            Utilidades.imprimir("RESULTADO: EMPATE");
        } else if (saludAtacante<=0) {
            d.setGanador(2);//GANA DESAFIADO
            Utilidades.imprimir("RESULTADO: GANA EL DESAFIADO");

            atacante.setOro(atacante.getOro()-d.getOroApostado());
            d.getDesafiante().setOro(d.getDesafiante().getOro()-d.getOroApostado());

            desafiado.setOro(desafiado.getOro()+d.getOroApostado());
            d.getDesafiado().setOro(d.getDesafiado().getOro()+d.getOroApostado());
        }else {
            d.setGanador(1);//GANA DESAFIANTE/ATACANTE
            Utilidades.imprimir("RESULTADO: GANA EL ATACANTE");

            atacante.setOro(atacante.getOro()+d.getOroApostado());
            d.getDesafiante().setOro(d.getDesafiante().getOro()+d.getOroApostado());

            desafiado.setOro(desafiado.getOro()-d.getOroApostado());
            d.getDesafiado().setOro(d.getDesafiado().getOro()-d.getOroApostado());
        }
        d.getDesafiado().setPj(desafiado);
        d.getDesafiante().setPj(atacante);
        d.setFecha(LocalDate.now());
        d.setRondas(rondas);
        d.setOroGanado(d.getOroApostado());
        Utilidades.pause(3);
        return d;
    }

    public int totalAtaque(int ataque){
        int exito = 0;
        for (int i=0;i<ataque;i++){
            Random random = new Random();
            int valor = random.nextInt(7);
            if (valor==5 || valor==6){
                exito++;
            }
        }
        return exito;
    }

    public int totalDefensa(int defensa){
        int exito = 0;
        for (int i=0;i<defensa;i++){
            Random random = new Random();
            int valor = random.nextInt(7);
            if (valor==5 || valor==6){
                exito++;
            }
        }
        return exito;
    }

    public void mostrarDesafios() throws InterruptedException, IOException, ClassNotFoundException {
        Utilidades.limpiarPantalla();
        cargarDatos();
        List<Desafio> aux = desafios;
        Utilidades.imprimir("LISTADO DESAFIOS (desde último reinicio): ");
        Utilidades.imprimir("");
        Utilidades.imprimir("--------------------------------------");
        Utilidades.imprimir("");
        for (int i = 0; i<aux.size() ;i++) {
            Desafio actual = desafios.get(i);
            Utilidades.imprimir("Desafío "+Integer.toString(i)+": ");
            Utilidades.imprimir("Nick desafiado: "+actual.getDesafiado().getNick());
            Utilidades.imprimir("Nick desafiante: "+actual.getDesafiante().getNick());
            Utilidades.imprimir("Fecha: "+actual.getFecha());
            if (!actual.isValidado())
                Utilidades.imprimir("Desafío NO validado");
            else {
                Utilidades.imprimir("Desafío validado.");
                if (actual.getGanador()>=0){
                    if (actual.getGanador()==0){
                        Utilidades.imprimir("Ganador: Empate");
                    }
                    else if (actual.getGanador()==1){
                        Utilidades.imprimir("Ganador: "+actual.getDesafiante().getNick());
                    }
                    else if (actual.getGanador()==2) {
                        Utilidades.imprimir(actual.getDesafiado().getNick());
                    }
                    Utilidades.imprimir("Rondas empleadas: "+actual.getRondas());
                    Utilidades.imprimir("Oro apostado: "+actual.getOroApostado());
                    Utilidades.imprimir("Oro ganado: "+actual.getOroGanado());
                }
                else
                    Utilidades.imprimir("Ganador: No disputado");
            }
            Utilidades.imprimir("");
            Utilidades.imprimir("--------------------------------------");
            Utilidades.imprimir("");
        }
        Utilidades.pause(3);
    }

    public void mostrarDesafios(Usuario u) throws InterruptedException, IOException, ClassNotFoundException {
        Utilidades.limpiarPantalla();
        cargarDatos();
        String nick = u.getNick();
        List<Desafio> aux = desafios;
        Utilidades.imprimir("LISTADO DESAFIOS (desde último reinicio): ");
        Utilidades.imprimir("");
        Utilidades.imprimir("--------------------------------------");
        Utilidades.imprimir("");
        for (int i = 0; i<aux.size() ;i++) {
            Desafio actual = desafios.get(i);
            if(!actual.isValidado())
                continue;
            if (actual.getGanador()<0)
                continue;
            if (!actual.getDesafiado().getNick().equals(nick) && !actual.getDesafiante().getNick().equals(nick))
                continue;
            Utilidades.imprimir("Nick desafiado: "+actual.getDesafiado().getNick());
            Utilidades.imprimir("Nick desafiante: "+actual.getDesafiante().getNick());
            Utilidades.imprimir("Fecha: "+actual.getFecha());
            if (!actual.isValidado())
                Utilidades.imprimir("Desafío NO validado");
            else {
                Utilidades.imprimir("Desafío validado.");
                if (actual.getGanador()>=0){
                    if (actual.getGanador()==0){
                        Utilidades.imprimir("Ganador: Empate");
                    }
                    else if (actual.getGanador()==1){
                        Utilidades.imprimir("Ganador: "+actual.getDesafiante().getNick());
                    }
                    else if (actual.getGanador()==2) {
                        Utilidades.imprimir(actual.getDesafiado().getNick());
                    }
                    Utilidades.imprimir("Rondas empleadas: "+actual.getRondas());
                    Utilidades.imprimir("Oro apostado: "+actual.getOroApostado());
                    Utilidades.imprimir("Oro ganado: "+actual.getOroGanado());
                }
                else
                    Utilidades.imprimir("Ganador: No disputado");
            }
            Utilidades.imprimir("");
            Utilidades.imprimir("--------------------------------------");
            Utilidades.imprimir("");
        }
        Utilidades.pause(4);
    }


    public boolean validarDesafio(int op) throws InterruptedException, IOException, ClassNotFoundException {
        this.cargarDatos();
        if (desafios.size()<=0){
            Utilidades.imprimir("No hay desafíos registrados por el momento.");
            Utilidades.imprimir("Volviendo...");
            Utilidades.pause(2);
            return false;
        }
        int t = this.desafios.size();
        if (op>t){
            Utilidades.imprimir("No existe ese desafío...");
            Utilidades.pause(2);
            return false;
        }
        if (op<0 || op>=desafios.size()){
            return false;
        }
        Desafio desafio = desafios.get(op);
        Desafio aux = desafio;
        Utilidades.imprimir("Si elije no validar el desafío, este se cancela.");
        int entero = Utilidades.pedirEntero("Desea validar es desafio? 0 = No / 1 = Si / otro = Cancelar");
        if (entero==1) {
            if (desafio.isValidado()){
                Utilidades.imprimir("Ya está validado...");
                Utilidades.pause(2);
                return false;
            }
            desafio.setValidado(true);
            desafios.remove(aux);
            //LAS FORTALEZAS Y DEBILIDADES ACTIVAS
            Personaje desafiado = desafio.getDesafiado().getPj();
            Personaje desafiante = desafio.getDesafiante().getPj();
            PersonajeController personajeController = new PersonajeController();
            desafiado = personajeController.activarFortalezas(desafiado);
            desafiado = personajeController.activarDebilidades(desafiado);
            desafiante = personajeController.activarFortalezas(desafiante);
            desafiante = personajeController.activarDebilidades(desafiante);
            desafio.getDesafiante().setPj(desafiante);
            desafio.getDesafiado().setPj(desafiado);
            desafio.setDesafiante(desafio.getDesafiante());
            desafio.setDesafiado(desafio.getDesafiado());
            desafios.add(desafio);
            //añadir el usuario a la lista de notificaciones
            //
            guardarDatos();
            Utilidades.imprimir("Desafío validado, volviendo...");
            Utilidades.pause(1);

            return true;
        }
        else if (entero==0){
            desafios.remove(aux);
            guardarDatos();
            Utilidades.imprimir("Desafío eliminado, volviendo...");
            Utilidades.pause(1);
            return false;
        }
        return false;
    }

    public Desafio rechazarDesafio(Desafio d) throws IOException, ClassNotFoundException {
        //cargarDatos();
        Desafio aux = d;
        desafios.remove(aux);
        d.setFecha(LocalDate.now());
        d.setGanador(1);
        d.setRondas(0);

        double oro = d.getOroApostado();
        oro = oro*0.10;
        d.setOroGanado((int) oro);
        Utilidades.imprimir("Oro perdido: "+(int) oro);

        d.getDesafiante().getPj().setOro(d.getDesafiante().getOro()+(int) oro);
        d.getDesafiado().getPj().setOro(d.getDesafiado().getOro()-(int) oro);
        d.getDesafiante().setOro(d.getDesafiante().getOro()+(int) oro);
        d.getDesafiado().setOro(d.getDesafiado().getOro()-(int) oro);

        desafios.add(d);
        guardarDatos();
        return d;
    }

    public void removeDesafio(Desafio d) throws IOException, ClassNotFoundException {
        this.desafios.remove(d);
        this.guardarDatos();
    }
}
