package co.edu.uniquindio.poo.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Representa una orden de compra en el restaurante.
 * Controla la modalidad de entrega, cálculo de totales, asignación de servicio de domicilio
 * y el ciclo de vida del pedido (PENDIENTE, CONFIRMADO, ENTREGADO, CANCELADO).
 *
 * @author Julian Gallego
 */
public class Pedido {

    private String codigo;
    private LocalDate fecha;
    private Cliente cliente;
    private ModalidadEntrega modalidad;
    private EstadoPedido estado;
    private double distanciaKm;
    private boolean entregaAceptada;
    private double costoDomicilio;
    private ServicioEntrega servicio;
    private ArrayList<DetallePedido> detalles;

    /**
     * Constructor principal de Pedido.
     *
     * @param codigo Código identificador único del pedido.
     * @param cliente Cliente que realiza la orden.
     * @param modalidad Modalidad de consumo (CONSUMO_RESTAURANTE o DOMICILIO).
     */
    public Pedido(String codigo, Cliente cliente, ModalidadEntrega modalidad) {
        this.codigo = codigo;
        this.fecha = LocalDate.now();
        this.cliente = cliente;
        this.modalidad = modalidad;
        this.estado = EstadoPedido.PENDIENTE;
        this.distanciaKm = 0.0;
        this.entregaAceptada = false;
        this.costoDomicilio = 0.0;
        this.servicio = null;
        this.detalles = new ArrayList<>();
    }

    /**
     * Agrega un producto con su cantidad a la lista de detalles del pedido.
     *
     * @param producto Producto seleccionado.
     * @param cantidad Unidades solicitadas.
     */
    public void agregarDetalle(Producto producto, int cantidad) {
        DetallePedido detalle = new DetallePedido(producto, cantidad);
        detalles.add(detalle);
    }

    /**
     * Asigna la empresa de transporte para pedidos a domicilio y liquida la tarifa si la distancia es aceptada.
     *
     * @param servicio Empresa de mensajería (ServicioEntrega).
     * @param distanciaKm Distancia en kilómetros hasta el destino.
     */
    public void asignarServicioDomicilio(ServicioEntrega servicio, double distanciaKm) {
        this.servicio = servicio;
        this.distanciaKm = distanciaKm;

        if (servicio != null) {
            if (servicio.aceptaRecorrido(distanciaKm) == true) {
                this.entregaAceptada = true;
                this.costoDomicilio = servicio.calcularCosto(distanciaKm);
            } else {
                this.entregaAceptada = false;
                this.costoDomicilio = 0.0;
            }
        }
    }

    /**
     * Verifica que el pedido cumpla con todas las condiciones obligatorias:
     * 1. Debe tener al menos un producto en los detalles.
     * 2. Todos los productos incluidos deben cumplir sus controles de calidad.
     * 3. Si es a domicilio, la empresa de mensajería debe haber aceptado la entrega.
     *
     * @return boolean true si cumple todas las reglas de negocio; false en caso contrario.
     */
    public boolean cumpleCondiciones() {
        if (detalles.isEmpty()) {
            return false;
        }

        for (int i = 0; i < detalles.size(); i++) {
            DetallePedido d = detalles.get(i);
            if (d.producto().cumpleCalidad() == false) {
                return false;
            }
        }

        if (modalidad == ModalidadEntrega.DOMICILIO) {
            if (entregaAceptada == false) {
                return false;
            }
        }

        return true;
    }

    /**
     * Confirma el pedido cambiando su estado a CONFIRMADO si se encuentra PENDIENTE y cumple todas las condiciones.
     *
     * @return boolean true si se confirmó exitosamente; false si fue rechazado.
     */
    public boolean confirmar() {
        if (estado == EstadoPedido.PENDIENTE && cumpleCondiciones() == true) {
            this.estado = EstadoPedido.CONFIRMADO;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Actualiza el estado del pedido a ENTREGADO cuando ya ha sido servido o despachado.
     */
    public void marcarEntregado() {
        if (estado == EstadoPedido.CONFIRMADO) {
            this.estado = EstadoPedido.ENTREGADO;
        }
    }

    /**
     * Cambia el estado del pedido a CANCELADO.
     */
    public void cancelar() {
        this.estado = EstadoPedido.CANCELADO;
    }

    /**
     * Suma el valor monetario de todos los productos incluidos en los detalles.
     *
     * @return double Subtotal acumulado.
     */
    public double calcularSubtotalProductos() {
        double subtotal = 0.0;
        for (int i = 0; i < detalles.size(); i++) {
            DetallePedido d = detalles.get(i);
            subtotal = subtotal + d.subtotal();
        }
        return subtotal;
    }

    /**
     * Calcula el valor total de la orden sumando los productos y el costo del flete de domicilio.
     *
     * @return double Total general a pagar.
     */
    public double calcularTotal() {
        return calcularSubtotalProductos() + costoDomicilio;
    }

    /**
     * Obtiene el código del pedido.
     *
     * @return String Código identificador.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Obtiene la fecha de registro del pedido.
     *
     * @return LocalDate Fecha de la orden.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el cliente asociado a la orden.
     *
     * @return Cliente Titular del pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la modalidad de entrega.
     *
     * @return ModalidadEntrega CONSUMO_RESTAURANTE o DOMICILIO.
     */
    public ModalidadEntrega getModalidad() {
        return modalidad;
    }

    /**
     * Obtiene el estado actual del pedido.
     *
     * @return EstadoPedido PENDIENTE, CONFIRMADO, ENTREGADO o CANCELADO.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Obtiene la distancia en kilómetros hacia el destino.
     *
     * @return double Distancia en km.
     */
    public double getDistanciaKm() {
        return distanciaKm;
    }

    /**
     * Indica si la entrega a domicilio fue aceptada por la empresa de envíos.
     *
     * @return boolean true si fue aceptada; false si fue rechazada.
     */
    public boolean isEntregaAceptada() {
        return entregaAceptada;
    }

    /**
     * Obtiene el costo liquidado por el servicio de domicilio.
     *
     * @return double Costo de transporte.
     */
    public double getCostoDomicilio() {
        return costoDomicilio;
    }

    /**
     * Obtiene el servicio de mensajería asignado.
     *
     * @return ServicioEntrega Empresa transportadora.
     */
    public ServicioEntrega getServicio() {
        return servicio;
    }

    /**
     * Obtiene la lista de detalles con los productos y cantidades.
     *
     * @return ArrayList de DetallePedido.
     */
    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {
        return "Pedido [" + codigo + "] - Cliente: " + cliente.getNombreCompleto() +
                " - Modalidad: " + modalidad + " - Total: $" + calcularTotal() + " (" + estado + ")";
    }
}
