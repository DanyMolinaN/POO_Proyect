package juego;

import java.io.*;
import java.util.Scanner;

public class Puntuaciones implements Serializable {
    private int puntaje;

    public Puntuaciones() {
        this.puntaje = 0;
    }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void guardarPuntaje(String nombreJugador) {
        try (FileOutputStream fos = new FileOutputStream("puntuaciones.dat", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(nombreJugador + ": " + puntaje);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarPuntuaciones() {
        try (FileInputStream fis = new FileInputStream("puntuaciones.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Object obj;
            while ((obj = ois.readObject()) != null) {
                System.out.println(obj);
            }
        } catch (EOFException eof) {
            // Fin del archivo alcanzado
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void restarPuntos(int puntosperdidos) {
       if(puntaje>0) {
           puntaje = puntaje - puntosperdidos;
       }
    }
}
