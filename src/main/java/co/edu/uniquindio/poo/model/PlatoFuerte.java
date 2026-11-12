package co.edu.uniquindio.poo.model;

public class PlatoFuerte extends Producto {
    private double pesoGramos;
    private TipoPreparacion preparacion;
    private boolean coccionAprobada;

    public PlatoFuerte(String codigo, String nombre, String descripcion, double precioBase,
                       int tiempoPreparacionMin, double pesoGramos, TipoPreparacion preparacion,
                       boolean coccionAprobada) {
        super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
        this.pesoGramos = pesoGramos;
        this.preparacion = preparacion;
        this.coccionAprobada = coccionAprobada;
    }

    @Override
    public double calcularPrecioVenta() {
        // Los platos fuertes tienen un incremento del 20%
        return precioBase * 1.20;
    }

    @Override
    public boolean cumpleCalidad() {
        // Peso entre 200 y 900 g, tiempo entre 10 y 60 min, y cocción aprobada
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
