package co.edu.uniquindio.poo.model;

/**
 * Clase abstracta que representa la estructura base de cualquier producto del restaurante.
 * Define los atributos comunes y los métodos polimórficos de cálculo de precio y control de calidad.
 *
 * @author Julian Gallego
 */
public abstract class Producto {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double precioBase;
    protected int tiempoPreparacionMin;

    /**
     * Constructor principal de Producto.
     *
     * @param codigo Identificador único del producto.
     * @param nombre Nombre comercial del producto.
     * @param descripcion Descripción de ingredientes o preparación.
     * @param precioBase Costo base sin incrementos ni impuestos.
     * @param tiempoPreparacionMin Tiempo estimado de elaboración en minutos.
     */
    public Producto(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacionMin) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    /**
     * Calcula el precio final de venta al público aplicando las reglas de incremento de la categoría.
     *
     * @return double Precio de venta calculado.
     */
    public abstract double calcularPrecioVenta();

    /**
     * Evalúa si el producto cumple con los estándares bromatológicos y de calidad requeridos.
     *
     * @return boolean true si cumple con los criterios de calidad; false en caso contrario.
     */
    public abstract boolean cumpleCalidad();

    /**
     * Retorna el identificador textual de la categoría a la que pertenece el producto.
     *
     * @return String Nombre de la categoría ("PLATOS_FUERTES", "BEBIDAS" o "POSTRES").
     */
    public abstract String getCategoria();

    /**
     * Obtiene el tiempo de preparación en minutos.
     *
     * @return int Minutos de preparación.
     */
    public int getTiempoPreparacionMin() {
        return tiempoPreparacionMin;
    }

    /**
     * Establece el tiempo de preparación en minutos.
     *
     * @param tiempoPreparacionMin Minutos de preparación.
     */
    public void setTiempoPreparacionMin(int tiempoPreparacionMin) {
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    /**
     * Obtiene el precio base del producto.
     *
     * @return double Precio base.
     */
    public double getPrecioBase() {
        return precioBase;
    }

    /**
     * Establece el precio base del producto.
     *
     * @param precioBase Nuevo precio base.
     */
    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return String Descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion Nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return String Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre Nuevo nombre comercial.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el código único del producto.
     *
     * @return String Código identificador.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código único del producto.
     *
     * @param codigo Nuevo código.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " - Precio Venta: $" + calcularPrecioVenta();
    }
}
