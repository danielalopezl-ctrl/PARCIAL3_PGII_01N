package co.edu.uniquindio.poo.model;

/**
 * Interfaz que define el contrato de operaciones que debe implementar
 * cualquier empresa de transporte o servicio de domicilios.
 *
 * @author Julian Gallego
 */
public interface ServicioEntrega {

    /**
     * Determina si la empresa acepta realizar un recorrido según la distancia en kilómetros.
     *
     * @param distanciaKm Distancia del trayecto en kilómetros.
     * @return boolean true si la distancia está dentro de la cobertura; false si excede el límite.
     */
    boolean aceptaRecorrido(double distanciaKm);

    /**
     * Calcula la tarifa a cobrar por el envío según la distancia recorrida y las políticas de la empresa.
     *
     * @param distanciaKm Distancia en kilómetros.
     * @return double Costo total del flete de domicilio.
     */
    double calcularCosto(double distanciaKm);

    /**
     * Retorna el nombre comercial de la empresa de transporte.
     *
     * @return String Nombre de la empresa.
     */
    String getNombre();
}
