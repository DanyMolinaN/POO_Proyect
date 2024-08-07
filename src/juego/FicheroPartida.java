package juego;

import java.io.Serializable;
import java.util.List;

public class FicheroPartida implements Serializable {
    private static final long serialVersionUID = 1L;

    private int playerScore;
    private int playerLives;
    private List<Enemigo> enemigos;
    private Nave nave;

    public FicheroPartida(int playerScore, int playerLives, List<Enemigo> enemigos, Nave nave) {
        this.playerScore = playerScore;
        this.playerLives = playerLives;
        this.enemigos = enemigos;
        this.nave = nave;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public Nave getNave() {
        return nave;
    }
}