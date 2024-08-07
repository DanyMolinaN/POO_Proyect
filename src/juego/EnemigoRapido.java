package juego;

import javax.swing.ImageIcon;
import java.awt.*;

public class EnemigoRapido extends Enemigo {
    public EnemigoRapido(int x, int y) {
        super(x, y);
        initEnemigo();
        this.imagenDerecha = new ImageIcon("src/recursos_gráficos/sprites/NaveEnemigaDER3.gif").getImage();
        this.imagenIzquierda = new ImageIcon("src/recursos_gráficos/sprites/NaveEnemigaIZQ3.gif").getImage();

    }

    @Override
    protected Image initEnemigo() {
        ImageIcon ii = new ImageIcon("src/recursos_gráficos/sprites/enemigo3.png");
        imagen = ii.getImage();
        vidas = 3;
        return null;
    }

    @Override
    public void actualizar() {
        // Movimiento y disparo se manejan en ControladorJuego
    }

    @Override
    public void dibujar(Graphics g) {
        if (moviendoDerecha) {
            g.drawImage(imagenDerecha, x, y, null);
        } else {
            g.drawImage(imagenIzquierda, x, y, null);
        }
    }
}
