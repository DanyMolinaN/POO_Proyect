package juego;

public class EstadoFase1 implements EstadoJefe {
    @Override
    public void atacar(JefeFinal jefe) {
        jefe.disparar();
    }

    @Override
    public void mover(JefeFinal jefe) {
        // Implementar la lógica de movimiento específica para Fase 1
        jefe.moverSuave(); // Llama al método de movimiento suave del jefe
    }

    @Override
    public void actualizar(JefeFinal jefe) {
        // Lógica de actualización específica para la fase 1
    }
}
