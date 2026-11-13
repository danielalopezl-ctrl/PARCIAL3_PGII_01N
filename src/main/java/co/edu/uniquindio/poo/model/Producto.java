package co.edu.uniquindio.poo.model;

/**
 * Clase abstracta que representa la estructura base de cualquier producto del restaurante.
 * Define los atributos comunes y los métodos polimórficos de cálculo de precio y control de calidad.
 *
 * @author Julian Gallego
 */
public abstract class Producto {
    public String codigo;
    public  String nombre;
    public  String descripcion;
    public  double precioBase;
    public  int tiempoPreparacionMin;

    /**
     * Metodo del constructor principal de Producto.
     *
     * @param codigo del producto
     * @param nombre del producto
     * @param descripcion del producto
     * @param precioBase del producto
     * @param tiempoPreparacionMin del producto
     */
    public Producto(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacionMin) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    public abstract double calcularPrecioVenta();

    public abstract boolean cumpleCalidad();

    public abstract String getCategoria();

    public int getTiempoPreparacionMin() {
        return tiempoPreparacionMin;
    }

    public void setTiempoPreparacionMin(int tiempoPreparacionMin) {
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " - Precio Venta: $" + calcularPrecioVenta();
    }
}
