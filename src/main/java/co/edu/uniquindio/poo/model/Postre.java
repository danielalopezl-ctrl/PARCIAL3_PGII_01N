package co.edu.uniquindio.poo.model;

/**
 * Representa un postre dentro del catálogo de productos.
 * Aplica un incremento del 15% (normal) o 20% (si requiere refrigeración) sobre el precio base.
 * Valida la calidad en función del peso y la temperatura de almacenamiento.
 *
 * @author Julian Gallego
 */
public class Postre extends Producto {

    private double pesoGramos;
    private double tempAlmacenamiento;
    private boolean requiereRefrigeracion;

    /**
     * Constructor principal de Postre.
     *
     * @param codigo Código identificador único del postre.
     * @param nombre Nombre comercial del postre.
     * @param descripcion Descripción de ingredientes.
     * @param precioBase Costo base sin incrementos.
     * @param tiempoPreparacionMin Tiempo de preparación en minutos.
     * @param pesoGramos Peso de la porción en gramos.
     * @param tempAlmacenamiento Temperatura de almacenamiento en grados Celsius.
     * @param requiereRefrigeracion Indica si el postre necesita cadena de frío.
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
     * Calcula el precio de venta del postre:
     * - Incremento del 20% si requiere refrigeración.
     * - Incremento del 15% si no requiere refrigeración.
     *
     * @return double Precio final de venta.
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
     * Evalúa si el postre cumple las condiciones de calidad y conservación:
     * - Peso entre 80.0 y 500.0 gramos.
     * - Si requiere refrigeración: Temperatura entre 0.0 °C y 6.0 °C.
     * - Si no requiere refrigeración: Temperatura ambiente entre 15.0 °C y 30.0 °C.
     *
     * @return boolean true si cumple con las condiciones; false en caso contrario.
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

    /**
     * Retorna la categoría del producto.
     *
     * @return String "POSTRES".
     */
    @Override
    public String getCategoria() {
        return "POSTRES";
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
     * Obtiene la temperatura de almacenamiento en grados Celsius.
     *
     * @return double Temperatura en °C.
     */
    public double getTempAlmacenamiento() {
        return tempAlmacenamiento;
    }

    /**
     * Indica si el postre requiere conservación en frío.
     *
     * @return boolean true si requiere refrigeración; false si se conserva a temperatura ambiente.
     */
    public boolean isRequiereRefrigeracion() {
        return requiereRefrigeracion;
    }
}
