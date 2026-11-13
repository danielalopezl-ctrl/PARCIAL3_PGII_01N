package co.edu.uniquindio.poo.model;

/**
 * Record que representa el detalle individual de un pedido.
 * Almacena el producto solicitado y la cantidad de unidades.
 *
 * @param producto Producto solicitado en la orden.
 * @param cantidad Número de unidades requeridas.
 *
 * @author Julian Gallego
 */
public record DetallePedido(Producto producto, int cantidad) {

    /**
     * Calcula el subtotal de este detalle multiplicando el precio de venta del producto por la cantidad.
     *
     * @return double Valor total de la línea de pedido.
     */
    public double subtotal() {
        return producto.calcularPrecioVenta() * cantidad;
    }
}
