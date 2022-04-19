package controlador;

public class Utilidades {

    public static void pause(int sec) throws InterruptedException {
        Thread.sleep(sec * 800);
    }
    public static void imprimir(String cadena){
        System.out.println(cadena);
    }
}
