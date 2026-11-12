package co.edu.uniquindio.poo.model;


import java.time.LocalDate;
import java.util.ArrayList;

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

    // Agrega un producto con su cantidad al pedido
    public void agregarDetalle(Producto producto, int cantidad) {
        DetallePedido detalle = new DetallePedido(producto, cantidad);
        detalles.add(detalle);
    }

    // Asigna la empresa de transporte y calcula el costo si la empresa acepta
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

    // Revisa que TODOS los productos del pedido cumplan las condiciones de calidad
    public boolean cumpleCondiciones() {
        if (detalles.isEmpty()) {
            return false;
        }

        // Bucle for tradicional para revisar cada detalle
        for (int i = 0; i < detalles.size(); i++) {
            DetallePedido d = detalles.get(i);
            if (d.producto().cumpleCalidad() == false) {
                return false; // Al menos un producto no cumple
            }
        }

        // Si es a domicilio, la empresa debe haber aceptado el servicio
        if (modalidad == ModalidadEntrega.DOMICILIO) {
            if (entregaAceptada == false) {
                return false;
            }
        }

        return true;
    }

    // Confirma el pedido si cumple todas las reglas
    public boolean confirmar() {
        if (estado == EstadoPedido.PENDIENTE && cumpleCondiciones() == true) {
            this.estado = EstadoPedido.CONFIRMADO;
            return true;
        } else {
            return false;
        }
    }

    public void marcarEntregado() {
        if (estado == EstadoPedido.CONFIRMADO) {
            this.estado = EstadoPedido.ENTREGADO;
        }
    }

    public void cancelar() {
        this.estado = EstadoPedido.CANCELADO;
    }

    // Suma el valor de todos los productos
    public double calcularSubtotalProductos() {
        double subtotal = 0.0;
        for (int i = 0; i < detalles.size(); i++) {
            DetallePedido d = detalles.get(i);
            subtotal = subtotal + d.subtotal();
        }
        return subtotal;
    }

    // Total general: subtotal + flete de domicilio
    public double calcularTotal() {
        return calcularSubtotalProductos() + costoDomicilio;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ModalidadEntrega getModalidad() {
        return modalidad;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public boolean isEntregaAceptada() {
        return entregaAceptada;
    }

    public double getCostoDomicilio() {
        return costoDomicilio;
    }

    public ServicioEntrega getServicio() {
        return servicio;
    }

    public ArrayList<DetallePedido> getDetalles() {
        return detalles;
    }

    @Override
    public String toString() {
        return "Pedido [" + codigo + "] - Cliente: " + cliente.getNombreCompleto() +
                " - Modalidad: " + modalidad + " - Total: $" + calcularTotal() + " (" + estado + ")";
    }
}
