package juego;

import java.awt.*;
import javax.swing.ImageIcon;

public class Defensa extends ObjetoDelJuego {
    private int vidas;

    public Defensa(int x, int y) {
        super(x, y);
        initDefensa();
    }

    private void initDefensa() {
        ImageIcon ii = new ImageIcon("src/recursos_gráficos/sprites/defensa.png");
        imagen = ii.getImage();
        this.imagen = ii.getImage();
        this.imagen = this.imagen.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        vidas = 30;
    }

    @Override
    public void actualizar() {
        // La defensa no se mueve
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, null);
    }

    public void quitarVida() {
        vidas--;
        if (vidas <= 0) {
            //setVisible(false);
        }
    }

    public boolean isVisible() {
        return vidas > 0;
    }
}
