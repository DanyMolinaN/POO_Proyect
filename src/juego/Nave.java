package juego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Nave extends ObjetoDelJuego {
    private static final long TIEMPO_DE_DISPARO = 100;
    public static long DURACION_INVULNERABILIDAD = 3000;
    private int dx, dy;
    private List<Misil> misiles;
    private long ultimoDisparo;
    private int vidas = 3;
    private int intentos = 3;
    private EstadoNave estado;

    public Nave(int x, int y) {
        super(x, y);
        inicializarJugador();
        estado = new EstadoNormal(this);
    }

    private void inicializarJugador() {
        ImageIcon spriteJugador = new ImageIcon("src/recursos_gráficos/sprites/Sprite-0001.png");
        imagen = spriteJugador.getImage();
        misiles = new ArrayList<>();
    }

    @Override
    public void actualizar() {
        estado.actualizar();
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, null);
        for (Misil misil : misiles) {
            misil.dibujar(g);
        }
    }

    public void keyPressed(KeyEvent e) {
        estado.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        estado.keyReleased(e);
    }

    public void disparar() {
        estado.disparar();
    }

    public void quitarVida() {
        estado.quitarVida();
    }

    public void realizarDisparo() {
        long time = System.currentTimeMillis();
        if (time - ultimoDisparo >= TIEMPO_DE_DISPARO) {
            misiles.add(new Misil(x + imagen.getWidth(null) / 2 - 2, y));
            ultimoDisparo = time;
        }
    }

    public void reducirVida() {
        vidas--;
        if (vidas <= 0) {
            vidas = 0; // Evitar vidas negativas
            if (intentos <= 0) {
                intentos = 0; // Evitar intentos negativos
                System.out.println("Game Over");
                // Lógica de fin del juego
            } else {
                ControladorJuego.reiniciarNivel();
                reiniciar();
            }
        } else {
            setEstado(new EstadoInvulnerable(this));
        }
    }

    protected void reiniciar() {
        x = 650;
        y = 600;
        vidas = 3;
        setEstado(new EstadoNormal(this)); // Reiniciar estado a normal
    }

    public void setEstado(EstadoNave estado) {
        this.estado = estado;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void actualizarMovimiento() {
        x += dx;
        y += dy;
        limitarMovimiento();
    }

    private void limitarMovimiento() {
        if (x < 250) x = 250;
        if (x > 926 - imagen.getWidth(null)) x = 926 - imagen.getWidth(null);
        if (y < 550) y = 550;
        if (y > 650 - imagen.getHeight(null)) y = 650 - imagen.getHeight(null);
    }

    public void actualizarMisiles() {
        List<Misil> misilesRemover = new ArrayList<>();
        for (Misil misil : misiles) {
            misil.actualizar();
            if (!misil.isVisible()) {
                misilesRemover.add(misil);
            }
        }
        misiles.removeAll(misilesRemover);
    }

    public int getVidas() {
        return vidas;
    }

    public int getIntentos() {
        return intentos;
    }

    public void quitarIntento() {
        if (intentos > 0) {
            intentos--;
        }
    }

    public List<Misil> getMisiles() {
        return misiles;
    }
}
