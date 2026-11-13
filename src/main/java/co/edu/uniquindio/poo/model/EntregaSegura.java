package co.edu.uniquindio.poo.model;

/**
 * Implementación de la empresa de mensajería EntregaSegura.
 * Cobertura máxima de 20 kilómetros.
 * Tarifa: $7.000 base + $1.000 por kilómetro. Aplica un recargo de $3.000 si supera los 5 km.
 *
 * @author Julian Gallego
 */
public class EntregaSegura implements ServicioEntrega {
    private double tarifaBase = 7000.0;
    private double tarifaPorKm = 1000.0;
    private double recargo = 3000.0;
    private double maxKm = 20.0;

    /**
     * Valida si el trayecto no supera los 20 kilómetros y es mayor a 0.
     *
     * @param distanciaKm Distancia en km.
     * @return boolean true si la distancia es positiva y menor o igual a 20 km.
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
     * Liquida el costo del envío: $7.000 base + ($1.000 * distanciaKm).
     * Si la distancia supera los 5 km, suma $3.000 de recargo por trayecto largo.
     *
     * @param distanciaKm Distancia en km.
     * @return double Costo total del envío.
     */
    @Override
    public double calcularCosto(double distanciaKm) {
        double total = tarifaBase + (tarifaPorKm * distanciaKm);

        if (distanciaKm > 5.0) {
            total = total + recargo;
        }

        return total;
    }

    /**
     * Retorna el nombre de la empresa.
     *
     * @return String "EntregaSegura".
     */
    @Override
    public String getNombre() {
        return "EntregaSegura";
    }
}
