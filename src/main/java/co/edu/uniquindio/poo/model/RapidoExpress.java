package co.edu.uniquindio.poo.model;

/**
 * Implementación de la empresa de mensajería RápidoExpress.
 * Cobertura máxima de 12 kilómetros.
 * Tarifa: $5.000 base + $1.500 por cada kilómetro recorrido.
 *
 * @author Julian Gallego
 */
public class RapidoExpress implements ServicioEntrega {
    private double tarifaBase = 5000.0;
    private double tarifaPorKm = 1500.0;
    private double maxKm = 12.0;

    /**
     * Valida si el trayecto no supera los 12 kilómetros y es mayor a 0.
     *
     * @param distanciaKm Distancia en km.
     * @return boolean true si la distancia es positiva y menor o igual a 12 km.
     */
    @Override
    public boolean aceptaRecorrido(double distanciaKm) {
        if (distanciaKm > 0 && distanciaKm <= maxKm) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Liquida el costo del envío: $5.000 base + ($1.500 * distanciaKm).
     *
     * @param distanciaKm Distancia en km.
     * @return double Costo total del envío.
     */
    @Override
    public double calcularCosto(double distanciaKm) {
        double total = tarifaBase + (tarifaPorKm * distanciaKm);
        return total;
    }

    /**
     * Retorna el nombre de la empresa.
     *
     * @return String "RápidoExpress".
     */
    @Override
    public String getNombre() {
        return "RápidoExpress";
    }
}
