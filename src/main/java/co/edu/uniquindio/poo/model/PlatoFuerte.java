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
     * Constructor principal de PlatoFuerte.
     *
     * @param codigo Código identificador único.
     * @param nombre Nombre del plato fuerte.
     * @param descripcion Detalle de ingredientes y acompañamientos.
     * @param precioBase Costo base del plato.
     * @param tiempoPreparacionMin Tiempo de preparación en minutos.
     * @param pesoGramos Peso de la porción en gramos.
     * @param preparacion Tipo o método de cocción empleado.
     * @param coccionAprobada Indicador de aprobación del control de cocción.
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
     * Calcula el precio de venta del plato fuerte aplicando un 20% de incremento sobre el precio base.
     *
     * @return double Precio final de venta.
     */
    @Override
    public double calcularPrecioVenta() {
        return precioBase * 1.20;
    }

    /**
     * Evalúa si el plato fuerte cumple las condiciones bromatológicas:
     * - Peso entre 200 y 900 gramos.
     * - Tiempo de preparación entre 10 y 60 minutos.
     * - Control de cocción aprobado.
     *
     * @return boolean true si cumple todos los criterios de calidad; false en caso contrario.
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

    /**
     * Retorna la categoría del producto.
     *
     * @return String "PLATOS_FUERTES".
     */
    @Override
    public String getCategoria() {
        return "PLATOS_FUERTES";
    }

    /**
     * Obtiene el peso de la porción en gramos.
     *
     * @return double Peso en gramos.
     */
    public double getPesoGramos() {
        return pesoGramos;
    }

    /**
     * Obtiene el método de preparación del plato.
     *
     * @return TipoPreparacion Método de cocción (ASADO, FRITO, HORNEADO, A_LA_PLANCHA).
     */
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
