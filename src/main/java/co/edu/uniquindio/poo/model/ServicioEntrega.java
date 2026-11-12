package co.edu.uniquindio.poo.model;

// Interfaz que define las operaciones comunes para cualquier empresa de envíos
public interface ServicioEntrega {
    boolean aceptaRecorrido(double distanciaKm);
    double calcularCosto(double distanciaKm);
    String getNombre();
}
