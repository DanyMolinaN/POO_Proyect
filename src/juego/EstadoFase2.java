package juego;

public class EstadoFase2 implements EstadoJefe {
    @Override
    public void atacar(JefeFinal jefe) {
        jefe.disparar();
        // Añadir lógica adicional si es necesario
    }

    @Override
    public void mover(JefeFinal jefe) {
        // Implementar la lógica de movimiento específica para Fase 2
        jefe.moverSuave(); // Llama al método de movimiento suave del jefe
    }

    @Override
    public void actualizar(JefeFinal jefe) {
        // Lógica de actualización específica para la fase 2
    }
}
