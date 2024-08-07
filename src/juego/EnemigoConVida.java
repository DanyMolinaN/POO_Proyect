package juego;

import javax.swing.ImageIcon;
import java.awt.*;

public class EnemigoConVida extends Enemigo {
    public EnemigoConVida(int x, int y) {
        super(x, y);
        initEnemigo();
        this.imagenDerecha = new ImageIcon("src/recursos_gráficos/sprites/NaveEnemigaDER2.gif").getImage();
        this.imagenIzquierda = new ImageIcon("src/recursos_gráficos/sprites/NaveEnemigaIZQ2.gif").getImage();
    }

    @Override
    protected Image initEnemigo() {
        ImageIcon ii = new ImageIcon("src/recursos_gráficos/sprites/enemigo4.png");
        imagen = ii.getImage();
        vidas = 3;

        return null;
    }

    @Override
    public void actualizar() {
        // Movimiento básico se controla en ControladorJuego
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
