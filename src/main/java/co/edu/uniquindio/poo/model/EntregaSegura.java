package co.edu.uniquindio.poo.model;

public class EntregaSegura implements ServicioEntrega {
    private double tarifaBase = 7000.0;
    private double tarifaPorKm = 1000.0;
    private double recargo = 3000.0;
    private double maxKm = 20.0;

    @Override
    public boolean aceptaRecorrido(double distanciaKm) {
        // Acepta hasta 20 kilómetros
        if (distanciaKm > 0 && distanciaKm <= maxKm) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double calcularCosto(double distanciaKm) {
        // Cobro: 7000 base + 1000 por kilómetro
        double total = tarifaBase + (tarifaPorKm * distanciaKm);

        // Si la distancia supera los 5 km, se le suman 3000 de recargo
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