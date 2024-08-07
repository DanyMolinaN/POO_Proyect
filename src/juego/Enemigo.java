package juego;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Enemigo extends ObjetoDelJuego {
    protected int vidas;
    boolean moviendoDerecha;
    Image imagenDerecha;
    Image imagenIzquierda;
    private Image imagenActual;

    public Enemigo(int x, int y) {
        super(x, y);
        imagenActual = initEnemigo();
        this.moviendoDerecha = true;
    }

    protected Image initEnemigo() {
        ImageIcon ii = new ImageIcon("src/recursos_gráficos/sprites/enemigo.png");
        imagen = ii.getImage();
        vidas = 1;
        return null;
    }

    @Override
    public void actualizar() {
        // Implementar lógica de movimiento común a todos los enemigos

    }

    @Override
    public abstract void dibujar(Graphics g);

    public void mover(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        this.moviendoDerecha = dx > 0;
        cambiarImagen();
    }

    public void cambiarImagen() {
        if (moviendoDerecha) {
            imagenActual = imagenDerecha;
        } else {
            imagenActual = imagenIzquierda;
        }
    }

    public void quitarVida() {
        vidas--;
    }

    public int getVidas() {
        return vidas;
    }
}
