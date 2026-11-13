package co.edu.uniquindio.poo.model;


public class RapidoExpress implements ServicioEntrega {
    private double tarifaBase = 5000.0;
    private double tarifaPorKm = 1500.0;
    private double maxKm = 12.0;

    /**
     * Metodo para Validar si el trayecto
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
     *Metodo para Liquidar el costo del envío
     *
     * @param distanciaKm Distancia en km.
     * @return
     */
    @Override
    public double calcularCosto(double distanciaKm) {
        double total = tarifaBase + (tarifaPorKm * distanciaKm);
        return total;
    }

    @Override
    public String getNombre() {
        return "RápidoExpress";
    }
}
