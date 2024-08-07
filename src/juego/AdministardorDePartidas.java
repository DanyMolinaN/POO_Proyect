package juego;

import java.io.*;

public class AdministardorDePartidas {

    public static void saveGameState(FicheroPartida ficheroPartida, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(ficheroPartida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FicheroPartida loadGameState(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (FicheroPartida) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}