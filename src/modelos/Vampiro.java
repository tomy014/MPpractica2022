package modelos;

import controlador.Utilidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vampiro implements Personaje, Serializable {

    private int puntosSangre;
    private int edad;

    private String nombre;
    private List<Arma> armas;
    private List<Armadura> armaduras;
    private List<Arma> armasActivas;
    private Armadura armaduraActiva;
    private List<Esbirro> esbirros;
    private int oro;
    private int salud;
    private int poder;
    private List<Debilidad> debilidades;
    private List<Fortaleza> fortalezas;
    private String habilidad;
    private int atqHab;
    private int defHab;
    private int costeHabilidad;


    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Arma> getArmas() {
        return armas;
    }

    @Override
    public void setArmas(List<Arma> armas) {
        this.armas = armas;
    }
    public void addArmas(Arma a) {
        this.armas.add(a);
    }

    public List<Armadura> getArmaduras() {
        return armaduras;
    }

    @Override
    public void setArmaduras(List<Armadura> armaduras) {
        this.armaduras = armaduras;
    }
    public void addArmaduras(Armadura a) {
        this.armaduras.add(a);
    }

    public List<Arma> getArmasActivas() {
        return armasActivas;
    }

    @Override
    public void setArmasActivas(List<Arma> armasActivas) {
        this.armasActivas = armasActivas;
    }
    public void addArmasActivas(Arma a) {
        this.armasActivas.add(a);
    }

    public Armadura getArmaduraActiva() {
        return armaduraActiva;
    }

    @Override
    public void setArmaduraActiva(Armadura armaduraActiva) {
        this.armaduraActiva = armaduraActiva;
    }

    public List<Esbirro> getEsbirros() {
        return esbirros;
    }

    @Override
    public void setEsbirros(List<Esbirro> esbirros) {
        this.esbirros = esbirros;
    }
    public void addEsbirro(Esbirro e){
        this.esbirros.add(e);
    }

    public int getOro() {
        return oro;
    }

    @Override
    public void setOro(int oro) {
        this.oro = oro;
    }

    public int getSalud() {
        return salud;
    }

    @Override
    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getPoder() {
        return poder;
    }

    @Override
    public void setPoder(int poder) {
        this.poder = poder;
    }

    public List<Debilidad> getDebilidades() {
        return debilidades;
    }

    @Override
    public void setDebilidades(List<Debilidad> d) {
        this.debilidades = d;
    }
    public void addDebilidades(Debilidad d) {
        this.debilidades.add(d);
    }

    public List<Fortaleza> getFortalezas() {
        return fortalezas;
    }

    @Override
    public void setFortalezas(List<Fortaleza> f) {
        this.fortalezas = f;
    }
    public void addFortalezas(Fortaleza f) {
        this.fortalezas.add(f);
    }

    @Override
    public void usarHabilidad() {
        Utilidades.imprimir("Se usó la disciplina " + getHabilidad() + ".");
        this.puntosSangre -= costeHabilidad;
    }

    @Override
    public void ganarRonda() {
        puntosSangre += 4;
        Utilidades.imprimir("Ronda ganada para " + this.nombre);
    }

    @Override
    public void perderRonda() {
        Utilidades.imprimir("Ronda perdida para " + this.nombre);
    }

    @Override
    public int calcularAtaque() {
        int ataqueTotal = 0;
        ataqueTotal += this.poder;
        ataqueTotal += this.armaduraActiva.getModAtq();
        for(int i = 0; i<this.armasActivas.size(); i++){
            if (this.armasActivas.get(i)==null)
                continue;
            ataqueTotal += this.armasActivas.get(i).getModAtq();
        }
        for(int i = 0; i<this.fortalezas.size(); i++){
            if (this.fortalezas.get(i)==null)
                continue;
            ataqueTotal += this.fortalezas.get(i).getValor();
        }
        for(int i = 0; i<this.debilidades.size(); i++){
            if (this.debilidades.get(i)==null)
                continue;
            ataqueTotal -= this.debilidades.get(i).getValor();
        }
        if(puntosSangre>=costeHabilidad){
            usarHabilidad();
            ataqueTotal += this.atqHab;
        }
        if(this.puntosSangre>=5)
            ataqueTotal += 2;
        return ataqueTotal;
    }

    @Override
    public int calcularDefensa() {
        int defensaTotal = 0;
        defensaTotal += this.poder;
        defensaTotal += armaduraActiva.getModDef();
        for(int i = 0; i<this.armasActivas.size(); i++){
            if (this.armasActivas.get(i)==null)
                continue;
            defensaTotal += this.armasActivas.get(i).getModDef();
        }
        for(int i = 0; i<this.fortalezas.size(); i++){
            if (this.fortalezas.get(i)==null)
                continue;
            defensaTotal += this.fortalezas.get(i).getValor();
        }
        for(int i = 0; i<this.debilidades.size(); i++){
            if (this.debilidades.get(i)==null)
                continue;
            defensaTotal -= this.debilidades.get(i).getValor();
        }
        if(puntosSangre>=costeHabilidad){
            usarHabilidad();
            defensaTotal += this.defHab;
        }
        if(this.puntosSangre>=5)
            defensaTotal += 2;
        return defensaTotal;
    }

    @Override
    public int saludEsbirros() {
        List<Esbirro> aux = this.esbirros;
        int suma = 0;
        if (aux==null)
            return 0;
        for (Esbirro e: aux) {
            if (e==null)
                continue;
            suma += e.calcularSalud();
        }
        return suma;
    }

    @Override
    public void modificarDatos() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("Aviso, este método no se puede cancelar,");
        Utilidades.imprimir("Si no quieres cambiar un valor, escribe el mismo.");
        Utilidades.imprimir("Nombre: "+this.nombre);
        setNombre(Utilidades.pedirCadena("Nuevo nombre: "));
        Utilidades.imprimir("Nombre habilidad: "+this.habilidad);
        setHabilidad(Utilidades.pedirCadena("Nuevo nomHabilidad: "));
        Utilidades.imprimir("Ataque habilidad: "+Integer.toString(this.atqHab));
        setAtqHab(Utilidades.pedirEntero("Nuevo valor: "));
        Utilidades.imprimir("Defensa habilidad: "+Integer.toString(this.defHab));
        setDefHab(Utilidades.pedirEntero("Nuevo valor: "));
        Utilidades.imprimir("Coste habilidad: "+this.costeHabilidad);
        setCosteHabilidad(Utilidades.pedirEntero("Nuevo valor: "));
        Utilidades.imprimir("Poder: "+this.poder);
        setPoder(Utilidades.pedirEntero("Nuevo valor: "));
        Utilidades.imprimir("Oro actual: "+this.oro);
        setOro(Utilidades.pedirEntero("Nuevo valor: "));
        Utilidades.imprimir("Salud del personaje: "+this.salud);
        setSalud(Utilidades.pedirEntero("Nuevo valor: "));

        Utilidades.imprimir("Edad: " + this.edad);
        setEdad(Utilidades.pedirEntero("Nuevo valor: "));
        Utilidades.imprimir("Puntos de sangre: " + this.puntosSangre);
        setPuntosSangre(Utilidades.pedirEntero("Nuevo valor: "));
    }

    @Override
    public Esbirro crearEsbirros() {
        Utilidades.limpiarPantalla();
        Utilidades.imprimir("1. Añadir Ghoul");
        Utilidades.imprimir("2. Añadir demonio");
        Utilidades.imprimir("Otro. Cancelar");
        int o = Utilidades.pedirEntero("Elije una opción:");
        if (o==1){
            String esbirroNombre = Utilidades.pedirCadena("Nombre del esbirro: ");
            int esbirroSalud = Utilidades.pedirEntero("Salud del esbirro: ");
            String esbirroDependencia = Utilidades.pedirCadena("Descripcion de su dependencia: ");
            int valorDependencia = Utilidades.pedirEntero("Valor de la dependencia: ");
            Ghouls ghoul = new Ghouls(esbirroNombre,esbirroSalud,valorDependencia,esbirroDependencia);
            return ghoul;
        }
        else if(o==2){
            String esbirroNombre = Utilidades.pedirCadena("Nombre del esbirro: ");
            int esbirroSalud = Utilidades.pedirEntero("Salud del esbirro: ");
            int e = Utilidades.pedirEntero("Si tiene otros esbirros pulse 1. ");
            Demonios demonio;
            List<Esbirro> subLista = new ArrayList<Esbirro>();
            while (e==1){
                subLista.add(crearEsbirros());
                e = Utilidades.pedirEntero("Si tiene otros esbirros este esbirro pulse 1. ");
            }
            demonio = new Demonios(esbirroNombre,esbirroSalud,subLista);
            Utilidades.imprimir("Lista de esbirros añadida.");
            /*
            else
                demonio = new Demonios(esbirroNombre,esbirroSalud,null);
            */
            return demonio;
        }
        else
            return null;
    }

    @Override
    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public String getHabilidad() {
        return habilidad;
    }

    @Override
    public void setAtqHab(int ataque) {
        this.atqHab = ataque;
    }

    public int getAtqHab(){
        return atqHab;
    }

    @Override
    public void setDefHab(int defensa) {
        this.defHab = defensa;
    }

    public int getDefHab(){
        return defHab;
    }


    public int getPuntosSangre() {
        return puntosSangre;
    }

    public void setPuntosSangre(int puntosSangre) {
        this.puntosSangre = puntosSangre;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public int getCosteHabilidad(){
        return costeHabilidad;
    }

    public void setCosteHabilidad(int coste){
        this.costeHabilidad = coste;
    }
}
