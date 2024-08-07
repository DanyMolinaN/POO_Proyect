package juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ControladorJuego {
    private static List<ObjetoDelJuego> objetos;
    private static Nave nave;
    private boolean moviendoDerecha = true;
    private static int VELOCIDAD = 1;
    private static final int DESPLAZAMIENTO_VERTICAL = 10;
    protected static final int LIMITE_DERECHO = 900;
    protected static final int LIMITE_IZQUIERDO = 200;
    private static int nivel = 1;
    private static PanelJuego panel;
    private static boolean juegoIniciado = false;
    private static JefeFinal jefeFinal;
    private static Puntuaciones puntuaciones;



    public ControladorJuego() {
        objetos = new ArrayList<>();
        nave = new Nave(600, 625);
        objetos.add(nave);
        inicializarDefensas();
        inicializarEnemigos();
        jefeFinal = new JefeFinal(150, 100);
        this.puntuaciones = new Puntuaciones();
    }

    public static void reiniciarNivel() {

        if (nave.getVidas() == 0 || algunEnemigoSuperaLimite()) {
            puntuaciones.restarPuntos(100);
            puntuaciones.restarPuntos(500);
            nave.quitarIntento();
            if (nave.getIntentos() == 0) {
                finalizarJuego();
                System.out.println("Fin del Juego");

            } else {
                juegoIniciado =false;
                pausarJuego(); // Pausa antes de reiniciar el nivel
                inicializarEnemigos();
                nave.reiniciar();
            }
        }
    }

    private static boolean algunEnemigoSuperaLimite() {
        return objetos.stream().anyMatch(obj -> obj instanceof Enemigo && obj.getY() > 500);
    }

    public static void pausarJuego() {
        new Thread(() -> {
            try {
                for (int i = 5; i > 0; i--) {
                    panel.mostrarContador(i);
                    Thread.sleep(1000);
                }
                juegoIniciado = true;
                panel.ocultarContador();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void eliminarObjeto(JefeFinal jefeFinal) {
        objetos.remove(jefeFinal);
    }


    public void setPanel(PanelJuego panel) {
        this.panel = panel;
    }

    private void inicializarDefensas() {
        int numDefensas = 4;
        int separacion = 200;
        int y = 500;

        for (int i = 0; i < numDefensas; i++) {
            int x = 250 + i * separacion;
            objetos.add(new Defensa(x, y));
        }
    }

    private static void inicializarEnemigos() {
        objetos.removeIf(obj -> obj instanceof Enemigo); // Limpiar enemigos de niveles anteriores

        switch (nivel) {
            case 1 -> initNivel1();
            case 2 -> initNivel2();
            case 3 -> initNivel3();
            case 4 -> initNivel4();
            case 5 -> initNivel5();
            case 6 -> iniciarBatallaConJefe();
            // Agregar más niveles según sea necesario
        }
    }

    private static void iniciarBatallaConJefe() {
        jefeFinal = new JefeFinal(550, 50);
        objetos.add(jefeFinal);
    }

    private static void initNivel1() {
        int filas = 5;
        int columnas = 10;
        int anchoEnemigo = 40;
        int altoEnemigo = 40;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = 300 + col * (anchoEnemigo + 10);
                int y = 10 + fila * (altoEnemigo + 10);
                objetos.add(new EnemigoBasico(x, y));
            }
        }
    }

    private static void initNivel2() {
        int filas = 5;
        int columnas = 10;
        int anchoEnemigo = 40;
        int altoEnemigo = 40;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = 300 + col * (anchoEnemigo + 10);
                int y = 10 + fila * (altoEnemigo + 10);
                if (fila == 0) {
                    objetos.add(new EnemigoBasico(x, y));
                } else if (fila == 1) {
                    objetos.add(new EnemigoRapido(x, y));
                } else {
                    objetos.add(new EnemigoConVida(x, y));
                }
            }
        }
    }

    private static void initNivel3() {
        int filas = 6;
        int columnas = 12;
        int anchoEnemigo = 40;
        int altoEnemigo = 40;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = 300 + col * (anchoEnemigo + 10);
                int y = 10 + fila * (altoEnemigo + 10);
                if (fila == 0) {
                    objetos.add(new EnemigoRapido(x, y));
                } else if (fila == 1) {
                    objetos.add(new EnemigoQueDispara(x, y));
                } else {
                    objetos.add(new EnemigoBasico(x, y));
                }
            }
        }
    }

    private static void initNivel4() {
        int filas = 7;
        int columnas = 8;
        int anchoEnemigo = 40;
        int altoEnemigo = 40;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = 300 + col * (anchoEnemigo + 10);
                int y = 10 + fila * (altoEnemigo + 10);
                if (fila == 0) {
                    objetos.add(new EnemigoRapido(x, y));
                } else if (fila == 1) {
                    objetos.add(new EnemigoQueDispara(x, y));
                } else {
                    objetos.add(new EnemigoBasico(x, y));
                }
            }
        }
    }

    private static void initNivel5() {
        int filas = 5;
        int columnas = 6;
        int anchoEnemigo = 40;
        int altoEnemigo = 40;
        for (int fila = 0; fila < filas; fila++) {
            for (int col = 0; col < columnas; col++) {
                int x = 250 + col * (anchoEnemigo + 10);
                int y = 10 + fila * (altoEnemigo + 10);
                if (fila == 0) {
                    objetos.add(new EnemigoRapido(x, y));
                } else if (fila == 1) {
                    objetos.add(new EnemigoQueDispara(x, y));
                } else {
                    objetos.add(new EnemigoBasico(x, y));
                }
            }
        }
    }

    public void actualizar() {
        reiniciarNivel();
        if (juegoIniciado) {
            if(nivel<6){
            nave.actualizar();
            moverEnemigos();
            actualizarDisparos();
            verificarColisiones();
            verificarNivelCompleto();
            }else {
                verificarColisiones();
                verificarColisionesConJefe();
                actualizarDisparos();
                jefeFinal.actualizar();
                nave.actualizar();
            }

        }
    }

    private void verificarColisionesConJefe() {
        List<Misil> misiles = nave.getMisiles();
        for (Misil misil : misiles) {
            if (jefeFinal != null && jefeFinal.getX() < misil.getX() + misil.getImagen().getWidth(null) &&
                    jefeFinal.getX() + jefeFinal.getImagen().getWidth(null) > misil.getX() &&
                    jefeFinal.getY() < misil.getY() + misil.getImagen().getHeight(null) &&
                    jefeFinal.getY() + jefeFinal.getImagen().getHeight(null) > misil.getY()) {
                misil.setVisible(false);
                 jefeFinal.quitarVida(10);
               if (jefeFinal.getVidas() <= 0) {
                   objetos.remove(jefeFinal);
                   puntuaciones.sumarPuntos(500);
                 objetos.remove(jefeFinal);
                }
                break;
            }
        }

    }






    private void moverEnemigos() {
        boolean cambiarDireccion = false;
        List<Enemigo> enemigosAMover = new ArrayList<>();

        for (ObjetoDelJuego obj : objetos) {
            if (obj instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) obj;
                enemigosAMover.add(enemigo);
            }
        }

        for (Enemigo enemigo : enemigosAMover) {
            if (moviendoDerecha) {
                enemigo.mover(VELOCIDAD, 0);
                if (enemigo.getX() >= LIMITE_DERECHO) {
                    cambiarDireccion = true;
                }
            } else {
                enemigo.mover(-VELOCIDAD, 0);
                if (enemigo.getX() <= LIMITE_IZQUIERDO) {
                    cambiarDireccion = true;
                }
            }

            enemigo.actualizar(); // Para actualizar los disparos de los enemigos

            // Verificar si algún enemigo llegó a la posición y > 500
            if (enemigo.getY() > 500) {
                reiniciarNivel();
                return; // Salir del método para evitar más movimientos en este ciclo
            }
        }

        if (cambiarDireccion) {
            for (Enemigo enemigo : enemigosAMover) {
                enemigo.mover(0, DESPLAZAMIENTO_VERTICAL);
            }
            moviendoDerecha = !moviendoDerecha;
        }
    }

    private void actualizarDisparos() {
        List<Misil> misiles = nave.getMisiles();
        List<Misil> misilesARemover = new ArrayList<>();

        for (Misil misil : misiles) {
            if (misil.isVisible()) {
                misil.actualizar();
            } else {
                misilesARemover.add(misil);
            }
        }

        misiles.removeAll(misilesARemover);

        // Actualizar misiles disparados por enemigos
        List<ObjetoDelJuego> misilesEnemigosARemover = new ArrayList<>();
        for (ObjetoDelJuego obj : objetos) {
            if (obj instanceof MisilEnemigo) {
                MisilEnemigo misil = (MisilEnemigo) obj;
                if (misil.isVisible()) {
                    misil.actualizar();
                } else {
                    misilesEnemigosARemover.add(misil);
                }
            }
        }

        objetos.removeAll(misilesEnemigosARemover);
    }

    private void verificarColisiones() {
        List<Misil> misiles = nave.getMisiles();
        for (Misil misil : misiles) {
            for (int i = 0; i < objetos.size(); i++) {
                ObjetoDelJuego obj = objetos.get(i);
                if (obj instanceof Enemigo) {
                    Enemigo enemigo = (Enemigo) obj;
                    if (misil.getX() < enemigo.getX() + enemigo.getImagen().getWidth(null) &&
                            misil.getX() + misil.getImagen().getWidth(null) > enemigo.getX() &&
                            misil.getY() < enemigo.getY() + enemigo.getImagen().getHeight(null) &&
                            misil.getY() + misil.getImagen().getHeight(null) > enemigo.getY()) {
                        misil.setVisible(false);
                        enemigo.quitarVida();
                        if (enemigo.getVidas() <= 0) {
                            objetos.remove(i);
                            if (enemigo instanceof EnemigoBasico) {
                                puntuaciones.sumarPuntos(100);
                            } else if (enemigo instanceof EnemigoRapido) {
                                puntuaciones.sumarPuntos(200);
                            } else if (enemigo instanceof EnemigoQueDispara) {
                                puntuaciones.sumarPuntos(300);
                            }
                        }
                        break;
                    }
                }
            }
        }

        // Colisión entre la nave y los enemigos
        for (ObjetoDelJuego obj : objetos) {
            if (obj instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) obj;
                if (nave.getX() < enemigo.getX() + enemigo.getImagen().getWidth(null) &&
                        nave.getX() + nave.getImagen().getWidth(null) > enemigo.getX() &&
                        nave.getY() < enemigo.getY() + enemigo.getImagen().getHeight(null) &&
                        nave.getY() + nave.getImagen().getHeight(null) > enemigo.getY()) {
                    nave.quitarVida();
                    // No eliminar al enemigo
                    break;
                }
            }
        }

        // Colisión entre la nave y los misiles enemigos
        for (int i = 0; i < objetos.size(); i++) {
            ObjetoDelJuego obj = objetos.get(i);
            if (obj instanceof MisilEnemigo) {
                MisilEnemigo misilEnemigo = (MisilEnemigo) obj;
                if (nave.getX() < misilEnemigo.getX() + misilEnemigo.getImagen().getWidth(null) &&
                        nave.getX() + nave.getImagen().getWidth(null) > misilEnemigo.getX() &&
                        nave.getY() < misilEnemigo.getY() + misilEnemigo.getImagen().getHeight(null) &&
                        nave.getY() + nave.getImagen().getHeight(null) > misilEnemigo.getY()) {
                    nave.quitarVida();
                    objetos.remove(i);
                    break;
                }
            }
        }

        // Colisión entre los misiles enemigos y la barrera (Defensa)
        for (int i = 0; i < objetos.size(); i++) {
            ObjetoDelJuego obj = objetos.get(i);
            if (obj instanceof MisilEnemigo) {
                MisilEnemigo misilEnemigo = (MisilEnemigo) obj;
                for (int j = 0; j < objetos.size(); j++) {
                    ObjetoDelJuego objDefensa = objetos.get(j);
                    if (objDefensa instanceof Defensa) {
                        Defensa defensa = (Defensa) objDefensa;
                        if (misilEnemigo.getX() < defensa.getX() + defensa.getImagen().getWidth(null) &&
                                misilEnemigo.getX() + misilEnemigo.getImagen().getWidth(null) > defensa.getX() &&
                                misilEnemigo.getY() < defensa.getY() + defensa.getImagen().getHeight(null) &&
                                misilEnemigo.getY() + misilEnemigo.getImagen().getHeight(null) > defensa.getY()) {
                            misilEnemigo.setVisible(false);
                            objetos.remove(i); // Remove the missile
                            defensa.quitarVida();
                            if (!defensa.isVisible()) {
                                objetos.remove(j);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private void verificarNivelCompleto() {
        boolean nivelCompleto = objetos.stream().noneMatch(obj -> obj instanceof Enemigo || obj instanceof JefeFinal);
        if (nivelCompleto) {
            VELOCIDAD += 1;
            nivel++;
            juegoIniciado = false;
            pausarJuego();
            if (panel != null) {
                panel.setNivel(nivel);
            }

            inicializarEnemigos();
        }
    }

    public static void agregarObjeto(ObjetoDelJuego objeto) {
        objetos.add(objeto);
    }

    public List<ObjetoDelJuego> getObjetos() {
        return objetos;
    }

    public Nave getJugador() {
        return nave;
    }
    public static void finalizarJuego() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombreJugador = scanner.nextLine();
        puntuaciones.guardarPuntaje(nombreJugador);
        Puntuaciones.mostrarPuntuaciones();
    }
}
