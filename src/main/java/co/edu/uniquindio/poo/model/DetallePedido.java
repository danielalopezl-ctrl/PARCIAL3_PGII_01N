package co.edu.uniquindio.poo.model;

// guarda el producto y la cantidad pedida

import co.edu.uniquindio.poo.model.Producto;

public record DetallePedido(Producto producto, int cantidad) {


    public double subtotal() {
        return producto.calcularPrecioVenta() * cantidad;
    }


}
