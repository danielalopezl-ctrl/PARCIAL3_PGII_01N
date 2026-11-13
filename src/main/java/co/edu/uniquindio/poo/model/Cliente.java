package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

/**
 * Representa a un cliente del restaurante.
 * Almacena información de contacto y el historial de pedidos confirmados.
 *
 * @author Julian Gallego
 */
public class Cliente {

    private String documento;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;
    private ArrayList<Pedido> pedidos;

    /**
     * Constructor principal de Cliente.
     *
     * @param documento Número de documento de identidad único.
     * @param nombreCompleto Nombre y apellidos del cliente.
     * @param telefono Número de contacto telefónico.
     * @param correo Dirección de correo electrónico.
     * @param direccion Dirección de residencia o entrega.
     */
    public Cliente(String documento, String nombreCompleto, String telefono, String correo, String direccion) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.pedidos = new ArrayList<>();
    }

    /**
     * Agrega un pedido confirmado al historial personal del cliente.
     *
     * @param pedido Instancia del pedido confirmado.
     */
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     * Retorna la lista de pedidos realizados por el cliente.
     *
     * @return ArrayList con el historial de pedidos.
     */
    public ArrayList<Pedido> verHistorial() {
        return pedidos;
    }

    /**
     * Obtiene el documento de identidad.
     *
     * @return String Documento.
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Obtiene el nombre completo del cliente.
     *
     * @return String Nombre completo.
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Obtiene el teléfono del cliente.
     *
     * @return String Teléfono.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Obtiene el correo electrónico.
     *
     * @return String Correo.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Obtiene la dirección de entrega o residencia.
     *
     * @return String Dirección.
     */
    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (Doc: " + documento + ")";
    }
}
