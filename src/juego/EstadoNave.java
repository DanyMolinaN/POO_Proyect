package juego;

import java.awt.event.KeyEvent;

public interface EstadoNave {
//    protected Nave nave;
//
//    public EstadoNave(Nave nave) {
//        this.nave = nave;
//    }

    void actualizar();
    void keyPressed(KeyEvent e);
    void keyReleased(KeyEvent e);
    void disparar();
    void quitarVida();
}
