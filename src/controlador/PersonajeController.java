package controlador;

import modelos.*;
import modelos.factory.CazadoresFactory;
import modelos.factory.LicantropoFactory;
import modelos.factory.VampiroFactory;

import java.util.ArrayList;
import java.util.List;

public class PersonajeController {

    /**
     * Recibe el personaje que quiero modificar armas ACTIVAS.
     * @param pj personaje a modificar
     * @return personaje modificado.
     * @throws InterruptedException
     */
    public Personaje cambiarArmas(Personaje pj) throws InterruptedException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("ARMAS: ");
        List<Arma> armasActivas = pj.getArmasActivas();
        if (armasActivas!=null){
            for (int i = 0; i < armasActivas.size(); i++) {
                Utilidades.imprimir("Arma actual: " + armasActivas.get(i).getNombre());
            }
        }
        Utilidades.imprimir("");
        List<Arma> disponibles = pj.getArmas();
        if (disponibles.size()<=0){
            Utilidades.imprimir("No tienes armas, volviendo...");
            Utilidades.pause(1);
            return pj;
        }
        for (int i = 0; i < disponibles.size(); i++) {
            Arma actual = disponibles.get(i);
            if(actual.isTipo())
                Utilidades.imprimir(i + ": "+ actual.getNombre() + ", de 2 manos");
            else
                Utilidades.imprimir(i + ": "+ actual.getNombre() + ", de 1 mano");
        }
        int num = Utilidades.pedirEntero("Elije nuevo arma, otro para cancelar: ");
        if (num <0 || num >=disponibles.size())
            return pj;
        pj.setArmasActivas(null);
        armasActivas = new ArrayList<>();
        pj.setArmasActivas(armasActivas);
        if (disponibles.get(num).isTipo()){
            armasActivas.add(disponibles.get(num));
            pj.setArmasActivas(armasActivas);
        }
        else {
            Arma arma1 = disponibles.get(num);
            armasActivas.add(arma1);
            num = Utilidades.pedirEntero("Elije otra arma de 1 mano u otro para cancelar: ");
            if (num <0 || num >=disponibles.size())
                return pj;
            Arma arma2 = disponibles.get(num);
            if (arma2.isTipo() || arma1.equals(arma2))
                Utilidades.imprimir("Arma no v??lida, operaci??n terminada.");
            else
                armasActivas.add(arma2);
            pj.setArmasActivas(armasActivas);
        }
        Utilidades.imprimir("Volviendo...");
        Utilidades.pause(2);
        return pj;
    }

    /**
     * Modificar armadura ACTIVA
     * @param pj
     * @return el pj con la armadura activa cambiada
     * @throws InterruptedException
     */
    public Personaje modificarArmadura(Personaje pj) throws InterruptedException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("ARMADURAS: ");
        Armadura armadura = pj.getArmaduraActiva();
        if (armadura!=null)
            Utilidades.imprimir("Armadura actual: "+armadura.getNombre());
        List<Armadura> disponibles = pj.getArmaduras();
        if (disponibles.size()<=0){
            Utilidades.imprimir("No tienes armaduras, volviendo...");
            Utilidades.pause(1);
            return pj;
        }
        for (int i = 0; i < disponibles.size(); i++) {
            Armadura actual = disponibles.get(i);
            Utilidades.imprimir(i + ": " + actual.getNombre());
        }
        int num = Utilidades.pedirEntero("Elije nueva armadura, otro para cancelar: ");
        if (num <0 || num >= disponibles.size())
            return pj;
        pj.setArmaduraActiva(disponibles.get(num));
        Utilidades.imprimir("Armadura cambiada. Volviendo...");
        Utilidades.pause(2);
        return pj;
    }

    /**
     * Modifica el usuario actual y registra un nuevo personaje, en caso de que no tenga uno ya.
     * @param u es el usuario en el que estamos..
     * @return Devuelve el usuario con su nuevo personaje ya a??adido.
     * @throws InterruptedException
     */
    public Usuario registrarPersonaje(Usuario u) throws InterruptedException {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("1. Vampiro");
        Utilidades.imprimir("2. Lic??ntropo");
        Utilidades.imprimir("3. Cazador");
        Utilidades.imprimir("0. Cancelar.");
        int n = -1;
        while (n < 0 || n > 3) {
            n = Utilidades.pedirEntero("Selecciona una opci??n: ");
        }
        if (n == 0)
            return u;
        if (n == 1){
            VampiroFactory personaje = new VampiroFactory();
            u.setPj(personaje.crearPersonaje());
        } else if (n ==2) {
            LicantropoFactory personaje = new LicantropoFactory();
            u.setPj(personaje.crearPersonaje());
        } else if (n==3) {
            CazadoresFactory personaje = new CazadoresFactory();
            u.setPj(personaje.crearPersonaje());
        }
        else
            Utilidades.imprimir("Elecci??n no v??lida.");
        Utilidades.imprimir("Personaje creado, volviendo...");
        Utilidades.pause(2);
        return u;
    }

    public Personaje modificarPersonaje(Personaje pj) {
        Utilidades.limpiarPantalla();
        //Llama al m??todo del personaje para modificarlo.
        Utilidades.imprimir("1. Editar Armas o armaduras");
        Utilidades.imprimir("2. Editar Fortalezas o debilidades");
        Utilidades.imprimir("3. A??adir equipamiento");
        Utilidades.imprimir("4. A??adir Fortalezas o debilidades");
        Utilidades.imprimir("5. A??adir esbirros");
        Utilidades.imprimir("6. Modificar sus datos o estad??sticas");
        Utilidades.imprimir("Otra. Cancelar operaci??n");
        int o = Utilidades.pedirEntero("Ellije una opci??n: ");
        switch (o){
            case 1:
                Utilidades.imprimir("1. Editar armas");
                Utilidades.imprimir("2. Editar armaduras");
                Utilidades.imprimir("Otro. Cancelar");
                o = Utilidades.pedirEntero("Elije una opci??n: ");
                if (o==1) {
                    return editarArmas(pj);
                }
                else if (o==2) {
                    return editarArmaduras(pj);
                }
                break;
            case 2:
                Utilidades.imprimir("1. Editar Fortalezas");
                Utilidades.imprimir("2. Editar debilidades");
                Utilidades.imprimir("Otro. Cancelar");
                o = Utilidades.pedirEntero("Elije una opci??n: ");
                if (o==1) {
                    return editarFortalezas(pj);
                }
                else if (o==2) {
                    return editarDebilidades(pj);
                }
                break;
            case 3:
                Utilidades.imprimir("1. A??adir arma");
                Utilidades.imprimir("2. A??adir armadura");
                Utilidades.imprimir("Otro. Cancelar");
                o = Utilidades.pedirEntero("Elije una opci??n: ");
                if (o==1) {
                    Arma nueva = crearArma();
                    List<Arma> aux = pj.getArmas();
                    if (aux==null)
                        aux= new ArrayList<>();
                    aux.add(nueva);
                    pj.setArmas(aux);
                }
                else if (o==2) {
                    Armadura nueva = crearArmadura();
                    List<Armadura> aux = pj.getArmaduras();
                    if (aux==null)
                        aux= new ArrayList<>();
                    aux.add(nueva);
                    pj.setArmaduras(aux);
                }
                break;
            case 4:
                Utilidades.imprimir("1. A??adir Fortaleza");
                Utilidades.imprimir("2. A??adir Debilidad");
                Utilidades.imprimir("Otro. Cancelar");
                o = Utilidades.pedirEntero("Elije una opci??n: ");
                if (o==1) {
                    Fortaleza nueva = crearFortaleza();
                    List<Fortaleza> aux = pj.getFortalezas();
                    if (aux==null)
                        aux= new ArrayList<>();
                    aux.add(nueva);
                    pj.setFortalezas(aux);
                }
                else if (o==2) {
                    Debilidad nueva = crearDebilidad();
                    List<Debilidad> aux = pj.getDebilidades();
                    if (aux==null)
                        aux= new ArrayList<>();
                    aux.add(nueva);
                    pj.setDebilidades(aux);
                }
                break;
            case 5:
                List<Esbirro> aux = pj.getEsbirros();
                if (aux==null)
                    aux= new ArrayList<>();
                Esbirro esbirro = pj.crearEsbirros();
                if (esbirro != null){
                    aux.add(esbirro);
                    pj.setEsbirros(aux);
                }
                break;
            case 6:
                pj.modificarDatos();
                return pj;
            default:
                return pj;
        }
        return pj;
    }

    private Fortaleza crearFortaleza() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Aviso, esta operaci??n no se puede cancelar.");
        Fortaleza fortaleza = new Fortaleza();
        fortaleza.setActivo(false);
        fortaleza.setNombre(Utilidades.pedirCadena("Nombre de la fortaleza: "));
        while (fortaleza.getNombre().equals(""))
            fortaleza.setNombre(Utilidades.pedirCadena("No puede estar vac??o: "));
        fortaleza.setValor(Utilidades.pedirEntero("Valor de la fortaleza: "));
        return fortaleza;
    }

    private Debilidad crearDebilidad() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Aviso, esta operaci??n no se puede cancelar.");
        Debilidad debilidad = new Debilidad();
        debilidad.setActivo(false);
        debilidad.setNombre(Utilidades.pedirCadena("Nombre de la debilidad: "));
        while (debilidad.getNombre().equals(""))
            debilidad.setNombre(Utilidades.pedirCadena("No puede estar vac??o: "));
        debilidad.setValor(Utilidades.pedirEntero("Valor de la debilidad: "));
        return debilidad;
    }

    private Armadura crearArmadura() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Aviso, esta operaci??n no se puede cancelar.");
        Armadura armadura = new Armadura();
        armadura.setNombre(Utilidades.pedirCadena("Nombre de la armadura: "));
        while (armadura.getNombre().equals(""))
            armadura.setNombre(Utilidades.pedirCadena("No puede estar vac??o: "));
        armadura.setModAtq(Utilidades.pedirEntero("Modificador de ataque: "));
        armadura.setModDef(Utilidades.pedirEntero("Modificador de defensa: "));
        return armadura;
    }

    private Arma crearArma() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Aviso, esta operaci??n no se puede cancelar.");
        Arma arma = new Arma();
        arma.setNombre(Utilidades.pedirCadena("Nombre del arma"));
        while (arma.getNombre().equals(""))
            arma.setNombre(Utilidades.pedirCadena("No puede estar vac??o: "));
        if (Utilidades.pedirEntero("Pulse 1 si es de dos manos, otro para una mano.") == 1)
            arma.setTipo(true);
        else
            arma.setTipo(false);
        arma.setModAtq(Utilidades.pedirEntero("Modificador de ataque: "));
        arma.setModDef(Utilidades.pedirEntero("Modificador de defensa: "));
        return arma;
    }

    private Personaje editarDebilidades(Personaje pj) {
        List<Debilidad> lista = pj.getDebilidades();
        if (lista==null){
            Utilidades.imprimir("El personaje no tiene debilidades");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        int t = lista.size();
        if (t<=0){
            Utilidades.imprimir("El personaje no tiene debilidades");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        for (int i = 0; i < t; i++) {
            Debilidad actual = lista.get(i);
            if (actual==null)
                continue;
            Utilidades.imprimir(i+". "+actual.getNombre());
        }
        Utilidades.imprimir(t+". Cancelar operaci??n.");
        int n = -1;
        while (n <0 || n > t){
            n = Utilidades.pedirEntero("Elije una debilidad");
        }
        if (n==t){
            return pj;
        }
        if (pj.getDebilidades().get(n)!=null)
            pj.getDebilidades().get(n).modificar();
        return pj;
    }

    private Personaje editarFortalezas(Personaje pj) {
        List<Fortaleza> lista = pj.getFortalezas();
        if (lista==null){
            Utilidades.imprimir("El personaje no tiene fortalezas");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        int t = lista.size();
        if (t<=0){
            Utilidades.imprimir("El personaje no tiene fortalezas");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        for (int i = 0; i < t; i++) {
            Fortaleza actual = lista.get(i);
            Utilidades.imprimir(i+". "+actual.getNombre());
        }
        Utilidades.imprimir(t+". Cancelar operaci??n.");
        int n = -1;
        while (n <0 || n > t){
            n = Utilidades.pedirEntero("Elije una fortaleza");
        }
        if (n==t){
            return pj;
        }
        if (pj.getFortalezas().get(n)!=null)
            pj.getFortalezas().get(n).modificar();
        return pj;
    }

    private Personaje editarArmas(Personaje pj) {
        List<Arma> lista = pj.getArmas();
        if (lista==null){
            Utilidades.imprimir("El personaje no tiene armas");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        int t = lista.size();
        if (t<=0){
            Utilidades.imprimir("El personaje no tiene armas");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        for (int i = 0; i < t; i++) {
            Arma actual = lista.get(i);
            Utilidades.imprimir(i+". "+actual.getNombre());
        }
        Utilidades.imprimir(t+". Cancelar operaci??n.");
        int n = -1;
        while (n <0 || n > t){
            n = Utilidades.pedirEntero("Elije un arma");
        }
        if (n==t){
            return pj;
        }
        if (pj.getArmas().get(n)!=null)
            pj.getArmas().get(n).modificar();
        pj.setArmasActivas(null);
        return pj;
    }

    private Personaje editarArmaduras(Personaje pj) {
        List<Armadura> lista = pj.getArmaduras();
        if (lista==null){
            Utilidades.imprimir("El personaje no tiene armaduras");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        int t = lista.size();
        if (t<=0){
            Utilidades.imprimir("El personaje no tiene armaduras");
            Utilidades.imprimir("Volviendo...");
            return pj;
        }
        for (int i = 0; i < t; i++) {
            Armadura actual = lista.get(i);
            if (actual==null)
                continue;
            Utilidades.imprimir(i+". "+actual.getNombre());
        }
        Utilidades.imprimir(t+". Cancelar operaci??n.");
        int n = -1;
        while (n <0 || n > t){
            n = Utilidades.pedirEntero("Elije una armadura");
        }
        if (n==t){
            return pj;
        }
        if (pj.getArmaduras().get(n)!=null)
            pj.getArmaduras().get(n).modificar();
        pj.setArmaduraActiva(null);
        return pj;
    }

    public Personaje activarFortalezas(Personaje pj){
        List<Fortaleza> lista = new ArrayList<Fortaleza>();
        Utilidades.imprimir("Lista de fortalezas de "+pj.getNombre());
        for (Fortaleza f:pj.getFortalezas()) {
            if (f==null)
                continue;
            int act = -1;
            Utilidades.imprimir("Fortaleza: "+f.getNombre());

            while (act<0 || act>1){
                act = Utilidades.pedirEntero("Activar 1, no activa 0");
            }
            if (act==0)
                f.setActivo(false);
            else
                f.setActivo(true);
            lista.add(f);
        }
        pj.setFortalezas(lista);
        return pj;
    }

    public Personaje activarDebilidades(Personaje pj){
        List<Debilidad> lista = new ArrayList<Debilidad>();
        Utilidades.imprimir("Lista de debilidades de "+pj.getNombre());
        for (Debilidad d:pj.getDebilidades()) {
            if (d==null)
                continue;
            int act = -1;
            Utilidades.imprimir("Debilidad: "+d.getNombre());
            while (act<0 || act>1){
                act = Utilidades.pedirEntero("Activar 1, no activa 0");
            }
            if (act==0)
                d.setActivo(false);
            else
                d.setActivo(true);
            lista.add(d);
        }
        pj.setDebilidades(lista);
        return pj;
    }

}
