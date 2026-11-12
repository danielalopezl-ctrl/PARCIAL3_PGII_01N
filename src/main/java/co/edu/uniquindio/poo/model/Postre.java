package co.edu.uniquindio.poo.model;

public class Postre extends Producto {

    private double pesoGramos;
    private double tempAlmacenamiento;
    private boolean requiereRefrigeracion;

    public Postre(String codigo, String nombre, String descripcion, double precioBase,
                  int tiempoPreparacionMin, double pesoGramos, double tempAlmacenamiento,
                  boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.pesoGramos = pesoGramos;
        this.tempAlmacenamiento = tempAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    @Override
    public double calcularPrecioVenta() {
        // Los postres tienen un incremento del 15%
        // Si requiere refrigeración, suman un 5% adicional (total 20%)
        if (requiereRefrigeracion == true) {
            return precioBase * 1.20;
        } else {
            return precioBase * 1.15;
        }
    }

    @Override
    public boolean cumpleCalidad() {
        // Peso entre 80 y 500 gramos
        if (pesoGramos < 80.0 || pesoGramos > 500.0) {
            return false;
        }

        // Si requiere refrigeración: entre 0 °C y 6 °C
        if (requiereRefrigeracion == true) {
            if (tempAlmacenamiento >= 0.0 && tempAlmacenamiento <= 6.0) {
                return true;
            } else {
                return false;
            }
        }

        // Si no requiere refrigeración: entre 15 °C y 30 °C
        if (requiereRefrigeracion == false) {
            if (tempAlmacenamiento >= 15.0 && tempAlmacenamiento <= 30.0) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public String getCategoria() {
        return "POSTRES";
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public double getTempAlmacenamiento() {
        return tempAlmacenamiento;
    }

    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }
}
