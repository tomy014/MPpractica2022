package controlador;

import java.util.Scanner;

public abstract class Utilidades {

    public static void pause(int sec) throws InterruptedException {
        Thread.sleep(sec * 800);
    }

    public static void imprimir(String cadena){
        System.out.println(cadena);
    }

    public static void limpiarPantalla(){
        for (int i = 0; i < 14; i++)
            System.out.println();
    }

    public static String pedirCadena(String cadena){
        imprimir(cadena);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static int pedirEntero(String cadena){
        imprimir(cadena);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
