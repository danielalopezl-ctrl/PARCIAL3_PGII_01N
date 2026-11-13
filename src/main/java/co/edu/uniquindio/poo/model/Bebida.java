package co.edu.uniquindio.poo.model;

public class Bebida extends Producto {
    private int volumenMl;
    private double temperaturaServicio;
    private TemperaturaServicio tipo;
    private boolean preparacionEspecial;

    /**
     * Metodo constructor de bebida
     * @param codigo de la bebida
     * @param nombre de la bebida
     * @param descripcion de la bebida.
     * @param precioBase de la bebida
     * @param tiempoPreparacionMin de la bebida
     * @param volumenMl de la bebida.
     * @param temperaturaServicio de la bebida
     * @param tipo de bebida
     * @param preparacionEspecial de la bebida
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
     * Metodo que calcula el precio de la bebida:
     * - Incremento base del 10%
     * - Si requiere preparación especial suma $3.000 fijos adicionales
     * @return
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
     * Metodo que evalúa si la bebida cumple los parámetros de calidad:
     *
     * @return
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


    @Override
    public String getCategoria() {
        return "BEBIDAS";
    }


    public int getVolumenMl() {
        return volumenMl;
    }


    public double getTemperaturaServicio() {
        return temperaturaServicio;
    }

    public TemperaturaServicio getTipo() {
        return tipo;
    }


    public boolean isPreparacionEspecial() {
        return preparacionEspecial;
    }
}
