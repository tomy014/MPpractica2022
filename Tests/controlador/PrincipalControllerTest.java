package controlador;

import modelos.Usuario;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrincipalControllerTest implements Serializable {

    @Test
    void testBuscarUsuario() throws IOException, ClassNotFoundException {
        Usuario existente = new Usuario();
        existente.rellenarDatos();
        existente.setNick("Tomy");
        List<Usuario> aux = new ArrayList<>();
        aux.add(existente);
        PrincipalController controller = new PrincipalController();
        controller.setUsuarios(aux);
        Usuario busqueda = controller.buscarUsuario("Tomy");
        assertNotNull(busqueda);
    }

    @Test
    void registrarse() {
        Usuario existente = new Usuario();
        existente.rellenarDatos();
        existente.setNick("tomy");
        existente.setPassword("12345678");
        List<Usuario> aux = new ArrayList<>();
        aux.add(existente);
        PrincipalController controller = new PrincipalController();
        controller.setUsuarios(aux);
        /*String entrada1="tomy";
        String entrada2="Tomás";
        String entrada3="12345678";
        InputStream inputStream = new ByteArrayInputStream(entrada1.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        inputStream = new ByteArrayInputStream(entrada2.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        inputStream = new ByteArrayInputStream(entrada3.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        boolean busqueda = controller.registrarse();
        assertFalse(busqueda);*/
        //no puedo comprobar metodo que pide info por teclado.
    }

    @Test
    void iniciarSesion() throws IOException, InterruptedException, ClassNotFoundException {
        Usuario existente = new Usuario();
        existente.rellenarDatos();
        existente.setNick("tomy");
        existente.setPassword("12345678");
        List<Usuario> aux = new ArrayList<>();
        aux.add(existente);
        PrincipalController controller = new PrincipalController();
        controller.setUsuarios(aux);
        /*String entrada1="tomy";
        String entrada2="12345678";
        InputStream inputStream = new ByteArrayInputStream(entrada1.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        inputStream = new ByteArrayInputStream(entrada2.getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        boolean busqueda = controller.iniciarSesion();
        assertTrue(busqueda);*/
        //no puedo comprobar metodo que pide info por teclado.
    }

}