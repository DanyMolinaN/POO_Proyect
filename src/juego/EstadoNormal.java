package juego;

import java.awt.event.KeyEvent;

public class EstadoNormal implements EstadoNave {

    private Nave nave;

    public EstadoNormal(Nave nave) {

        this.nave = nave;
    }

    @Override
    public void actualizar() {
        nave.actualizarMovimiento();
        nave.actualizarMisiles();
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
        nave.reducirVida();
    }
}
