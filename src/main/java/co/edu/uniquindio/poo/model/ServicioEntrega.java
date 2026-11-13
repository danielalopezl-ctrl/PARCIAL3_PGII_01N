package co.edu.uniquindio.poo.model;

public interface ServicioEntrega {

    /**
     * metodo para Determinar si la empresa acepta realizar un recorrido según la distancia en kilómetros.
     *
     * @param distanciaKm para el domiciliario
     * @return
     */
    boolean aceptaRecorrido(double distanciaKm);

    /**
     * Metodo para Calcular la tarifa a cobrar por el envío según la distancia recorrida .
     *
     * @param distanciaKm del domiciliario
     * @return }
     */
    double calcularCosto(double distanciaKm);


    String getNombre();
}
