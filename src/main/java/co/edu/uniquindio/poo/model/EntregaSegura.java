package co.edu.uniquindio.poo.model;

public class EntregaSegura implements ServicioEntrega {
    private double tarifaBase = 7000.0;
    private double tarifaPorKm = 1000.0;
    private double recargo = 3000.0;
    private double maxKm = 20.0;

    /**
     * Metodo para validar si el trayecto no supera los 20 kilómetros y es mayor a 0.
     *
     * @param distanciaKm Distancia en km.
     * @return
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
     * Metodo para calcular la distancia de reccorido + recargo
     * @param distanciaKm Distancia en km.
     * @return
     */
    @Override
    public double calcularCosto(double distanciaKm) {
        double total = tarifaBase + (tarifaPorKm * distanciaKm);

        if (distanciaKm > 5.0) {
            total = total + recargo;
        }

        return total;
    }

    @Override
    public String getNombre() {
        return "EntregaSegura";
    }
}
