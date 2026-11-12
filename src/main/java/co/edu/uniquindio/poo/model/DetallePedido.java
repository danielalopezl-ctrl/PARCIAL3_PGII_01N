package co.edu.uniquindio.poo;

// guarda el producto y la cantidad pedida

public record DetallePedido(Producto producto, int cantidad) {

    // Metodo que calcula el subtotal de esta línea

    public double subtotal() {
        return producto.calcularPrecioVenta() * cantidad;
    }
}
