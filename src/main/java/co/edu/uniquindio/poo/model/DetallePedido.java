package co.edu.uniquindio.poo.model;


public record DetallePedido(Producto producto, int cantidad) {


    public double subtotal() {
        return producto.calcularPrecioVenta() * cantidad;
    }
}
