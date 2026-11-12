package co.edu.uniquindio.poo;

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

    // Getters tradicionales
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getTiempoPreparacionMin() {
        return tiempoPreparacionMin;
    }

    @Override
    public String toString() {
        return "[" + codigo + "] " + nombre + " - Precio Venta: $" + calcularPrecioVenta();
    }
}