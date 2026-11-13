package co.edu.uniquindio.poo.model;

public class Postre extends Producto {

    private double pesoGramos;
    private double tempAlmacenamiento;
    private boolean requiereRefrigeracion;

    /**
     * Metodo constructor principal de la clase Postre.
     *
     * @param codigo del postre
     * @param nombre del postre
     * @param descripcion del postre
     * @param precioBase del postre
     * @param tiempoPreparacionMin del postre
     * @param pesoGramos del postre
     * @param tempAlmacenamiento del postre
     * @param requiereRefrigeracion del postre
     */
    public Postre(String codigo, String nombre, String descripcion, double precioBase,
                  int tiempoPreparacionMin, double pesoGramos, double tempAlmacenamiento,
                  boolean requiereRefrigeracion) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.pesoGramos = pesoGramos;
        this.tempAlmacenamiento = tempAlmacenamiento;
        this.requiereRefrigeracion = requiereRefrigeracion;
    }

    /**
     * Metodo para calcular el precio de venta del postre
     * @return
     */
    @Override
    public double calcularPrecioVenta() {
        if (requiereRefrigeracion == true) {
            return precioBase * 1.20;
        } else {
            return precioBase * 1.15;
        }
    }

    /**
     * Metodo para evalar si el postre cumple las condiciones de calidad y conservación
     * @return
     */
    @Override
    public boolean cumpleCalidad() {
        if (pesoGramos < 80.0 || pesoGramos > 500.0) {
            return false;
        }

        if (requiereRefrigeracion == true) {
            if (tempAlmacenamiento >= 0.0 && tempAlmacenamiento <= 6.0) {
                return true;
            } else {
                return false;
            }
        }

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
