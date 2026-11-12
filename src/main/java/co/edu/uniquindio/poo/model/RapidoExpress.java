package co.edu.uniquindio.poo.model;

public class RapidoExpress implements ServicioEntrega {
    private double tarifaBase = 5000.0;
    private double tarifaPorKm = 1500.0;
    private double maxKm = 12.0;

    // @Override: indica la sobreescritura de metodos polimorficos que vienen de la clase abstract Producto o interfaz ServicioEntrega

    @Override
    public boolean aceptaRecorrido(double distanciaKm) {
        // Acepta hasta 12 kilómetros
        if (distanciaKm > 0 && distanciaKm <= maxKm) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public double calcularCosto(double distanciaKm) {
        // Cobro: 5000 fijos + 1500 por cada kilómetro
        double total = tarifaBase + (tarifaPorKm * distanciaKm);
        return total;
    }

    @Override
    public String getNombre() {
        return "RápidoExpress";
    }
}
