package co.edu.uniquindio.poo.model;

public abstract class Producto {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected double precioBase;
    protected int tiempoPreparacionMin;

    public Producto(String codigo, String nombre, String descripcion, double precioBase, int tiempoPreparacionMin) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    // Métodos abstractos que cada clase hija implementará con sus reglas
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