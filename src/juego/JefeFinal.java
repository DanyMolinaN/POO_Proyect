package juego;

import java.awt.*;
import javax.swing.ImageIcon;

public class JefeFinal extends ObjetoDelJuego {
    private int vidas;
    private EstadoJefe estadoActual;
    private long ultimoAtaque;
    private long tiempoEntreAtaques;
    private long tiempoInicioMovimiento;
    private double velocidadMovimiento;
    private double amplitudMovimiento;
    private double frecuenciaMovimiento;
    private int direccionX;
    private int direccionY;
    private static final int LIMITE_DERECHO = 900;
    private static final int LIMITE_IZQUIERDO = 200;
    private static final int LIMITE_INFERIOR = 500;
    private static final int LIMITE_SUPERIOR = 0;

    public JefeFinal(int x, int y) {
        super(x, y);
        inicializarJefe();
        this.vidas = 500;
        this.estadoActual = new EstadoFase1();
        this.ultimoAtaque = System.currentTimeMillis();
        this.tiempoEntreAtaques = 3000;
        this.tiempoInicioMovimiento = System.currentTimeMillis();
        this.velocidadMovimiento = 0.002; // Ajustar la velocidad del movimiento
        this.amplitudMovimiento = 25; // Ajustar la amplitud del movimiento
        this.frecuenciaMovimiento = 0.0001; // Ajustar la frecuencia del movimiento
        this.direccionX = 1; // Inicialmente se mueve a la derecha
        this.direccionY = 1; // Inicialmente se mueve hacia abajo
    }

    private void inicializarJefe() {
        ImageIcon spriteJefe = new ImageIcon("src/recursos_gráficos/sprites/JefeFinal3.png");
        this.imagen = spriteJefe.getImage();
        this.imagen = this.imagen.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    }

    public void actualizar() {
        long tiempoActual = System.currentTimeMillis();

        if (tiempoActual - ultimoAtaque > tiempoEntreAtaques) {
            estadoActual.atacar(this);
            ultimoAtaque = tiempoActual;
        }

        moverSuave();

        //estadoActual.actualizar(this);

        cambiarFaseSiEsNecesario();
    }

    public void cambiarFase(int nuevaFase) {
        switch (nuevaFase) {
            case 2:
                this.estadoActual = new EstadoFase2();
                break;
            case 3:
                this.estadoActual = new EstadoFase3();
                break;
            default:
                break;
        }
        this.tiempoEntreAtaques = Math.max(500, tiempoEntreAtaques - 500);
        this.velocidadMovimiento = 1; // Aumentar lentamente la velocidad
    }

    public void cambiarFaseSiEsNecesario() {
        if (vidas <= 50 && estadoActual instanceof EstadoFase1) {
            cambiarFase(2);
        } else if (vidas <= 20 && estadoActual instanceof EstadoFase2) {
            cambiarFase(3);
        }
    }

    public void moverSuave() {
        long tiempoActual = System.currentTimeMillis();
        double tiempoTranscurrido = (tiempoActual - tiempoInicioMovimiento) * frecuenciaMovimiento;

        // Movimiento suave basado en funciones trigonométricas
        int nuevoX = (int) (getX() + Math.sin(tiempoTranscurrido) * amplitudMovimiento * direccionX);
        int nuevoY = (int) (getY() + Math.cos(tiempoTranscurrido) * amplitudMovimiento * direccionY);

        // Ajustar dirección al llegar a los límites
        if (nuevoX <= LIMITE_IZQUIERDO || nuevoX >= LIMITE_DERECHO - getImagen().getWidth(null)) {
            direccionX *= -1; // Cambiar dirección horizontal
        }
        if (nuevoY <= LIMITE_SUPERIOR || nuevoY >= LIMITE_INFERIOR - getImagen().getHeight(null)) {
            direccionY *= -1; // Cambiar dirección vertical
        }

        // Asegurarse de que el jefe se mantenga dentro de los límites del juego
        if (nuevoX < LIMITE_IZQUIERDO) {
            nuevoX = LIMITE_IZQUIERDO;
        }
        if (nuevoX > LIMITE_DERECHO - getImagen().getWidth(null)) {
            nuevoX = LIMITE_DERECHO - getImagen().getWidth(null);
        }
        if (nuevoY < LIMITE_SUPERIOR) {
            nuevoY = LIMITE_SUPERIOR;
        }
        if (nuevoY > LIMITE_INFERIOR - getImagen().getHeight(null)) {
            nuevoY = LIMITE_INFERIOR - getImagen().getHeight(null);
        }

        setX(nuevoX);
        setY(nuevoY);
    }

    public void quitarVida(int cantidad) {
        this.vidas -= cantidad;
    }

    public int getVidas() {
        return vidas;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(getImagen(), getX(), getY(), null);
    }

    public Image cargarImagen(String ruta) {
        ImageIcon icon = new ImageIcon(ruta);
        return icon.getImage();
    }

    public void disparar() {
        MisilEnemigo misil = new MisilEnemigo(getX() + getImagen().getWidth(null) / 2 - 2, getY() + getImagen().getHeight(null));
        ControladorJuego.agregarObjeto(misil);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
