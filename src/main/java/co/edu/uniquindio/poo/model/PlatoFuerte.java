package co.edu.uniquindio.poo.model;

public class PlatoFuerte extends Producto {
    private double pesoGramos;
    private TipoPreparacion preparacion;
    private boolean coccionAprobada;

    /**
     * Metodo constructor de la clase plato fuerte.
     *
     * @param codigo del plato
     * @param nombre del plato
     * @param descripcion del plato
     * @param precioBase del plato
     * @param tiempoPreparacionMin del plato
     * @param pesoGramos del plato
     * @param preparacion del plato
     * @param coccionAprobada del plato
     */
    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase,
                       int tiempoPreparacionMin, double pesoGramos, TipoPreparacion preparacion,
                       boolean coccionAprobada) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.pesoGramos = pesoGramos;
        this.preparacion = preparacion;
        this.coccionAprobada = coccionAprobada;
    }

    /**
     * Metodo para calcular el precio de venta del plato fuerte.
     *
     * @return double Precio final de venta.
     */
    @Override
    public double calcularPrecioVenta() {
        return getPrecioBase() * 1.20;
    }

    /**
     * Metodo para evaluar si el plato fuerte cumple las condiciones de calidad.
     *
     * @return boolean true si cumple todos los criterios de calidad; false en caso contrario.
     */
    @Override
    public boolean cumpleCalidad() {
        if (pesoGramos >= 200 && pesoGramos <= 900) {
            if (getTiempoPreparacionMin() >= 10 && getTiempoPreparacionMin() <= 60) {
                if (coccionAprobada == true) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String getCategoria() {
        return "PLATOS_FUERTES";
    }

    public double getPesoGramos() {
        return pesoGramos;
    }

    public TipoPreparacion getPreparacion() {
        return preparacion;
    }

    /**
     * Indica si el plato superó el control de cocción.
     *
     * @return boolean true si la cocción fue aprobada; false en caso contrario.
     */
    public boolean isCoccionAprobada() {
        return coccionAprobada;
    }
}
