package juego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelJuego extends JPanel {
    private ControladorJuego controlador;
    private Timer timer;
    private int nivelActual;
    private int contadorInicio = 0;  // Inicializado en -1 para no mostrar el contador por defecto
    private boolean mostrarContador = false;
    private Image fondo;  // Imagen de fondo

    public PanelJuego(ControladorJuego controlador) {
        this.controlador = controlador;
        this.controlador.setPanel(this);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new ManejadorEntrada(controlador.getJugador()));
        nivelActual = 1;

        // Crear y empezar el timer
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.actualizar();
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar el fondo
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }

        for (ObjetoDelJuego objeto : controlador.getObjetos()) {
            objeto.dibujar(g);
        }

        // Mostrar mensaje de nivel
        g.setColor(Color.WHITE);
        g.drawString("Nivel: " + nivelActual, 10, 20);
        // Mostrar vidas e intentos
        g.drawString("Vidas: " + controlador.getJugador().getVidas(), 10, 40);
        g.drawString("Intentos: " + controlador.getJugador().getIntentos(), 10, 60);

        // Mostrar contador de inicio si es necesario
        if (mostrarContador) {
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.setColor(Color.RED);
            g.drawString(String.valueOf(contadorInicio), getWidth() / 2 - 15, getHeight() / 2);
        }
    }

    public void setNivel(int nivel) {
        nivelActual = nivel;
        cargarFondo();  // Cargar la imagen de fondo cuando se cambia el nivel
    }

    private void cargarFondo() {
        String path = "src/recursos_gráficos/fondos/level" + nivelActual + ".gif";  // Ruta de las imágenes de fondo
        ImageIcon icon = new ImageIcon(path);
        fondo = icon.getImage();
    }

    public int mostrarContador(int contador) {
        contadorInicio = contador;
        mostrarContador = true;
        repaint();
        return contadorInicio;
    }

    public void ocultarContador() {
        mostrarContador = false;
        repaint();
    }
}
