package co.edu.uniquindio.poo.model;

/**
 * Representa una bebida en el menú del restaurante.
 * Aplica un incremento del 10% sobre el precio base y suma $3.000 si requiere preparación especial.
 * Valida calidad según volumen y rango de temperatura de servicio (fría o caliente).
 *
 * @author Julian Gallego
 */
public class Bebida extends Producto {
    private int volumenMl;
    private double temperaturaServicio;
    private TemperaturaServicio tipo;
    private boolean preparacionEspecial;

    /**
     * Constructor principal de Bebida.
     *
     * @param codigo Código identificador de la bebida.
     * @param nombre Nombre de la bebida.
     * @param descripcion Descripción de la bebida.
     * @param precioBase Costo base sin incrementos.
     * @param tiempoPreparacionMin Tiempo de preparación en minutos.
     * @param volumenMl Volumen de la bebida en mililitros.
     * @param temperaturaServicio Temperatura medida en grados Celsius.
     * @param tipo Clasificación térmica (FRIA o CALIENTE).
     * @param preparacionEspecial Indica si requiere técnicas especiales de preparación.
     */
    public Bebida(String codigo, String nombre, String descripcion, double precioBase,
                  int tiempoPreparacionMin, int volumenMl, double temperaturaServicio,
                  TemperaturaServicio tipo, boolean preparacionEspecial) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.volumenMl = volumenMl;
        this.temperaturaServicio = temperaturaServicio;
        this.tipo = tipo;
        this.preparacionEspecial = preparacionEspecial;
    }

    /**
     * Calcula el precio de venta de la bebida:
     * - Incremento base del 10%.
     * - Si requiere preparación especial suma $3.000 fijos adicionales.
     *
     * @return double Precio final de venta.
     */
    @Override
    public double calcularPrecioVenta() {
        double total = precioBase * 1.10;

        if (preparacionEspecial == true) {
            total = total + 3000.0;
        }

        return total;
    }

    /**
     * Evalúa si la bebida cumple los parámetros de calidad:
     * - Volumen entre 150 y 1500 ml.
     * - Si es FRIA: Temperatura entre 0.0 °C y 10.0 °C.
     * - Si es CALIENTE: Temperatura entre 55.0 °C y 85.0 °C.
     *
     * @return boolean true si cumple con los rangos establecidos; false en caso contrario.
     */
    @Override
    public boolean cumpleCalidad() {
        if (volumenMl < 150 || volumenMl > 1500) {
            return false;
        }

        if (tipo == TemperaturaServicio.FRIA) {
            if (temperaturaServicio >= 0.0 && temperaturaServicio <= 10.0) {
                return true;
            } else {
                return false;
            }
        }

        if (tipo == TemperaturaServicio.CALIENTE) {
            if (temperaturaServicio >= 55.0 && temperaturaServicio <= 85.0) {
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
     * @return String "BEBIDAS".
     */
    @Override
    public String getCategoria() {
        return "BEBIDAS";
    }

    /**
     * Obtiene el volumen en mililitros.
     *
     * @return int Volumen en ml.
     */
    public int getVolumenMl() {
        return volumenMl;
    }

    /**
     * Obtiene la temperatura de servicio en grados Celsius.
     *
     * @return double Temperatura en °C.
     */
    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    /**
     * Obtiene el tipo de servicio térmico de la bebida.
     *
     * @return TemperaturaServicio FRIA o CALIENTE.
     */
    public TemperaturaServicio getTipo() {
        return tipo;
    }

    /**
     * Indica si la bebida requiere preparación especial.
     *
     * @return boolean true si requiere preparación especial; false si es regular.
     */
    public boolean isPreparacionEspecial() {
        return preparacionEspecial;
    }
}
