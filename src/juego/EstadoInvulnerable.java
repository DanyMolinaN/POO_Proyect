package juego;

import java.awt.event.KeyEvent;

public class EstadoInvulnerable implements EstadoNave {

    private long tiempoInicio;
    private Nave nave;

    public EstadoInvulnerable(Nave nave) {
        this.nave = nave;

        tiempoInicio = System.currentTimeMillis();
    }

    @Override
    public void actualizar() {
        nave.actualizarMovimiento();
        nave.actualizarMisiles();
        if (System.currentTimeMillis() - tiempoInicio > Nave.DURACION_INVULNERABILIDAD) {
            nave.setEstado(new EstadoNormal(nave));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT -> nave.setDx(-2);
            case KeyEvent.VK_RIGHT -> nave.setDx(2);
            case KeyEvent.VK_UP -> nave.setDy(-2);
            case KeyEvent.VK_DOWN -> nave.setDy(2);
            case KeyEvent.VK_Z -> disparar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> nave.setDx(0);
            case KeyEvent.VK_UP, KeyEvent.VK_DOWN -> nave.setDy(0);
        }
    }

    @Override
    public void disparar() {
        nave.realizarDisparo();
    }

    @Override
    public void quitarVida() {
        // No hace nada porque es invulnerable.
    }
}
