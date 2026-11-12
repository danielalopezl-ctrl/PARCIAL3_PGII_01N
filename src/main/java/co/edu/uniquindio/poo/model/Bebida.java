package co.edu.uniquindio.poo.model;


    public class Bebida extends Producto {
        private int volumenMl;
        private double temperaturaServicio;
        private TemperaturaServicio tipo;
        private boolean preparacionEspecial;

        public Bebida(String codigo, String nombre, String descripcion, double precioBase,
                      int tiempoPreparacionMin, int volumenMl, double temperaturaServicio,
                      TemperaturaServicio tipo, boolean preparacionEspecial) {
            super(codigo, nombre, descripcion, precioBase, tiempoPreparacionMin);
            this.volumenMl = volumenMl;
            this.temperaturaServicio = temperaturaServicio;
            this.tipo = tipo;
            this.preparacionEspecial = preparacionEspecial;
        }

        @Override
        public double calcularPrecioVenta() {
            // Las bebidas tienen un incremento del 10%
            double total = precioBase * 1.10;

            // Si requiere preparación especial suma $3.000 fijos
            if (preparacionEspecial == true) {
                total = total + 3000.0;
            }

            return total;
        }

        @Override
        public boolean cumpleCalidad() {
            // Volumen entre 150 y 1500 ml
            if (volumenMl < 150 || volumenMl > 1500) {
                return false;
            }

            // Si es fría: entre 0 °C y 10 °C
            if (tipo == TemperaturaServicio.FRIA) {
                if (temperaturaServicio >= 0.0 && temperaturaServicio <= 10.0) {
                    return true;
                } else {
                    return false;
                }
            }

            // Si es caliente: entre 55 °C y 85 °C
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

