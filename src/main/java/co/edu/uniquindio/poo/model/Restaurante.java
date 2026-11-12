package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Restaurante {

    private String nombreComercial;
    private String nit;
    private String direccion;

    // Listas normales de objetos
    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.productos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // 1. REGISTRAR PRODUCTO (valida que no exista el código)
    public boolean registrarProducto(Producto nuevoProducto) {
        if (nuevoProducto == null) {
            return false;
        }

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCodigo().equalsIgnoreCase(nuevoProducto.getCodigo())) {
                return false; // Ya existe ese código
            }
        }

        productos.add(nuevoProducto);
        return true;
    }

    // 2. REGISTRAR CLIENTE (valida que no exista el documento)
    public boolean registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente == null) {
            return false;
        }

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getDocumento().equals(nuevoCliente.getDocumento())) {
                return false; // Ya existe ese documento
            }
        }

        clientes.add(nuevoCliente);
        return true;
    }

    // Buscar cliente por documento
    public Cliente buscarCliente(String documento) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    // Buscar producto por código
    public Producto buscarProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    // Buscar pedido por código
    public Pedido buscarPedido(String codigo) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    // 3. REGISTRAR Y CONFIRMAR PEDIDO
    public boolean registrarPedido(Pedido nuevoPedido) {
        if (nuevoPedido == null) {
            return false;
        }

        // Revisar que no haya otro pedido con el mismo código
        if (buscarPedido(nuevoPedido.getCodigo()) != null) {
            return false;
        }

        // Intenta confirmar el pedido (valida calidad bromatológica y transporte)
        boolean sePudoConfirmar = nuevoPedido.confirmar();
        if (sePudoConfirmar == false) {
            return false;
        }

        // Si se confirmó, se agrega a la lista del restaurante y al historial del cliente
        pedidos.add(nuevoPedido);
        nuevoPedido.getCliente().agregarPedido(nuevoPedido);
        return true;
    }

    // 4. CALCULAR INGRESOS PROPIOS (Comida y bebidas de pedidos confirmados o entregados)
    public double calcularIngresosPropios() {
        double total = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getEstado() == EstadoPedido.CONFIRMADO || p.getEstado() == EstadoPedido.ENTREGADO) {
                total = total + p.calcularSubtotalProductos();
            }
        }
        return total;
    }

    // 5. CALCULAR INGRESOS POR DOMICILIOS
    public double calcularIngresosDomicilios() {
        double total = 0.0;
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getEstado() == EstadoPedido.CONFIRMADO || p.getEstado() == EstadoPedido.ENTREGADO) {
                total = total + p.getCostoDomicilio();
            }
        }
        return total;
    }

    public double calcularIngresos() {
        return calcularIngresosPropios() + calcularIngresosDomicilios();
    }

    // Reporte: Lista de productos que NO cumplen calidad
    public ArrayList<Producto> obtenerProductosNoCumplenCalidad() {
        ArrayList<Producto> malos = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.cumpleCalidad() == false) {
                malos.add(p);
            }
        }
        return malos;
    }

    // Reporte: Producto con más unidades vendidas
    public Producto obtenerProductoMasVendido() {
        Producto masVendido = null;
        int maxCantidad = 0;

        for (int i = 0; i < productos.size(); i++) {
            Producto prod = productos.get(i);
            int cantidadVendida = 0;

            for (int j = 0; j < pedidos.size(); j++) {
                Pedido ped = pedidos.get(j);
                if (ped.getEstado() == EstadoPedido.CONFIRMADO || ped.getEstado() == EstadoPedido.ENTREGADO) {
                    for (int k = 0; k < ped.getDetalles().size(); k++) {
                        DetallePedido d = ped.getDetalles().get(k);
                        if (d.producto().getCodigo().equalsIgnoreCase(prod.getCodigo())) {
                            cantidadVendida = cantidadVendida + d.cantidad();
                        }
                    }
                }
            }

            if (cantidadVendida > maxCantidad) {
                maxCantidad = cantidadVendida;
                masVendido = prod;
            }
        }

        return masVendido;
    }

    // Reporte: Categoría con mayores ingresos
    public String obtenerCategoriaMayorIngreso() {
        double ventasPlatos = 0.0;
        double ventasBebidas = 0.0;
        double ventasPostres = 0.0;

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido ped = pedidos.get(i);
            if (ped.getEstado() == EstadoPedido.CONFIRMADO || ped.getEstado() == EstadoPedido.ENTREGADO) {
                for (int j = 0; j < ped.getDetalles().size(); j++) {
                    DetallePedido d = ped.getDetalles().get(j);
                    String cat = d.producto().getCategoria();

                    if (cat.equals("PLATOS_FUERTES")) {
                        ventasPlatos = ventasPlatos + d.subtotal();
                    } else if (cat.equals("BEBIDAS")) {
                        ventasBebidas = ventasBebidas + d.subtotal();
                    } else if (cat.equals("POSTRES")) {
                        ventasPostres = ventasPostres + d.subtotal();
                    }
                }
            }
        }

        if (ventasPlatos >= ventasBebidas && ventasPlatos >= ventasPostres) {
            return "PLATOS_FUERTES ($" + ventasPlatos + ")";
        } else if (ventasBebidas >= ventasPlatos && ventasBebidas >= ventasPostres) {
            return "BEBIDAS ($" + ventasBebidas + ")";
        } else {
            return "POSTRES ($" + ventasPostres + ")";
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }
}
