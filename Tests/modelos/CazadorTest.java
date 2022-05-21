package modelos;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CazadorTest {

    @Test
    void usarHabilidad() {
        //son solo voids
    }

    @Test
    void ganarRonda() {
        //son solo voids
    }

    @Test
    void perderRonda() {
        //son solo voids
    }

    @Test
    void calcularAtaque() {
        Cazador cazador = new Cazador();
        List<Esbirro> esbirros = new ArrayList<>();
        esbirros.add(new Ghouls("",3,1,""));
        esbirros.add(new Demonios("",2,null));
        esbirros.add(new Humanos("",2,2));
        cazador.setEsbirros(esbirros);
        cazador.setVoluntad(1);
        cazador.setAtqHab(2);
        cazador.setDefHab(1);
        cazador.setPoder(5);
        cazador.setArmaduraActiva(null);
        cazador.setArmasActivas(new ArrayList<>());
        cazador.setFortalezas(new ArrayList<>());
        cazador.setDebilidades(new ArrayList<>());
        int resultado = cazador.calcularAtaque();
        assertEquals(8,resultado);
    }

    @Test
    void calcularDefensa() {
        Cazador cazador = new Cazador();
        List<Esbirro> esbirros = new ArrayList<>();
        esbirros.add(new Ghouls("",3,1,""));
        esbirros.add(new Demonios("",2,null));
        esbirros.add(new Humanos("",2,2));
        cazador.setEsbirros(esbirros);
        cazador.setVoluntad(1);
        cazador.setAtqHab(2);
        cazador.setDefHab(1);
        cazador.setPoder(5);
        cazador.setArmaduraActiva(null);
        cazador.setArmasActivas(new ArrayList<>());
        cazador.setFortalezas(new ArrayList<>());
        cazador.setDebilidades(new ArrayList<>());
        int resultado = cazador.calcularDefensa();
        assertEquals(7,resultado);
    }

    @Test
    void saludEsbirros() {
        Cazador cazador = new Cazador();
        List<Esbirro> esbirros = new ArrayList<>();
        esbirros.add(new Ghouls("",3,1,""));
        esbirros.add(new Demonios("",2,null));
        esbirros.add(new Humanos("",2,2));
        cazador.setEsbirros(esbirros);
        int resultado = cazador.saludEsbirros();
        assertEquals(7,resultado);
    }
}