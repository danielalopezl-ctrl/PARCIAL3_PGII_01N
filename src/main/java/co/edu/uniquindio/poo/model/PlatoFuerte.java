package co.edu.uniquindio.poo.model;

/**
 * Representa un plato fuerte dentro del menú del restaurante.
 * Aplica un incremento del 20% al precio base y valida condiciones de cocción, peso y tiempo.
 *
 * @author Julian Gallego
 */
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
     * Metodo para calcula el precio de venta del plato fuerte.
     *
     * @return
     */
    @Override
    public double calcularPrecioVenta() {
        return precioBase * 1.20;
    }

    /**
     * Metodo para Evaluar si el plato fuerte cumple las condiciones
     * @return
     */
    @Override
    public boolean cumpleCalidad() {
        if (pesoGramos >= 200 && pesoGramos <= 900) {
            if (tiempoPreparacionMin >= 10 && tiempoPreparacionMin <= 60) {
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


    public boolean isCoccionAprobada() {
        return coccionAprobada;
    }
}
