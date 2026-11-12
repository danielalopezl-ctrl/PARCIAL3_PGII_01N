package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Cliente {

    private String documento;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;
    private ArrayList<Pedido> pedidos;

    public Cliente(String documento, String nombreCompleto, String telefono, String correo, String direccion) {
        this.documento = documento;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.pedidos = new ArrayList<>();
    }

    // Agrega un pedido confirmado al historial
    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    // Retorna la lista de pedidos realizados
    public ArrayList<Pedido> verHistorial() {
        return pedidos;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public String toString() {
        return nombreCompleto + " (Doc: " + documento + ")";
    }
}
