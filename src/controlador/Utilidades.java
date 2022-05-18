package controlador;

import modelos.Desafio;
import modelos.Operador;
import modelos.Usuario;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    public static List<Usuario> getListaUsuarios() throws IOException, ClassNotFoundException {
        List<Usuario> lista = new ArrayList<Usuario>();
        try {
            File file = new File("listaUsuarios.dat");
            if (!file.exists()){
                file.createNewFile();
                return lista;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaUsuarios.dat"));
            Object aux = ois.readObject();
            while (aux!=null) {
                if (aux instanceof Operador)
                    lista.add((Operador) aux);
                else if (aux instanceof Usuario)
                    lista.add((Usuario) aux);
                aux = ois.readObject();
            }
            ois.close();
        }
        catch (EOFException e1)
        {
            //Fin del fichero.
        }
        return lista;
    }

    public static void setListaUsuarios(List<Usuario> lista) throws IOException {
        File file = new File("listaUsuarios.dat");
        if (file.exists()){
            file.delete();
            file.createNewFile();
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaUsuarios.dat"));
        for (int i = 0; i <lista.size(); i++) {
            if (lista.get(i)==null)
                continue;
            Usuario usuario = lista.get(i);
            oos.writeObject(usuario);
        }
        oos.close();
    }

    public static List<Desafio> getListaDesafios() throws IOException, ClassNotFoundException {
        List<Desafio> lista = new ArrayList<Desafio>();
        try {
            File file = new File("listaDesafios.dat");
            if (!file.exists()){
                file.createNewFile();
                return lista;
            }
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listaDesafios.dat"));
            Object aux = ois.readObject();
            while (aux!=null) {
                if (aux instanceof Desafio)
                    lista.add((Desafio) aux);
                aux = ois.readObject();
            }
            ois.close();
        }
        catch (EOFException e1)
        {
            //Fin del fichero.
        }
        return lista;
    }

    public static void setListaDesafios(List<Desafio> lista) throws IOException {
        File file = new File("listaDesafios.dat");
        if (file.exists()){
            file.delete();
            file.createNewFile();
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("listaDesafios.dat"));
        for (int i = 0; i <lista.size(); i++) {
            if (lista.get(i)==null)
                continue;
            Desafio desafio = lista.get(i);
            oos.writeObject(desafio);
        }
        oos.close();
    }
}