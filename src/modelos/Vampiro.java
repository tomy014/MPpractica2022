package modelos;

import controlador.Utilidades;

import java.util.List;

public class Vampiro implements Personaje {

    private int puntosSangre;

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
    private int edad;


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
    public void setDebilidades(List<Debilidad> Debilidad) {
        this.debilidades = debilidades;
    }
    public void addDebilidades(Debilidad d) {
        this.debilidades.add(d);
    }

    public List<Fortaleza> getFortalezas() {
        return fortalezas;
    }

    @Override
    public void setFortalezas(List<Fortaleza> fortalezas) {
        this.fortalezas = fortalezas;
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

    }

    @Override
    public void perderRonda() {

    }

    @Override
    public void calcularAtaque() {

    }

    @Override
    public void calcularDefensa() {

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
