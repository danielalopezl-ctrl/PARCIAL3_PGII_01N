package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

public class Restaurante {

    private String nombreComercial;
    private String nit;
    private String direccion;

    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    /**
     * Metodo constructor principal de Restaurante.
     *
     * @param nombreComercial del restaurante
     * @param nit del restaurante
     * @param direccion del restaurante
     */
    public Restaurante(String nombreComercial, String nit, String direccion) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
        this.direccion = direccion;
        this.productos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    /**
     * Metodo para Registrar un nuevo producto en el menú del restaurante.
     * @param nuevoProducto para el restaurante
     * @return
     */
    public boolean registrarProducto(Producto nuevoProducto) {
        if (nuevoProducto == null) {
            return false;
        }

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCodigo().equalsIgnoreCase(nuevoProducto.getCodigo())) {
                return false;
            }
        }

        productos.add(nuevoProducto);
        return true;
    }

    /**
     * Metodo para  Registrar un nuevo cliente en el sistema.
     *
     * @param nuevoCliente para el restaurante
     * @return
     */
    public boolean registrarCliente(Cliente nuevoCliente) {
        if (nuevoCliente == null) {
            return false;
        }

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getDocumento().equals(nuevoCliente.getDocumento())) {
                return false;
            }
        }

        clientes.add(nuevoCliente);
        return true;
    }

    /**
     * Metodo para buscar un cliente en el sistema por su número de documento.
     *
     * @param documento del cliente
     * @return
     */
    public Cliente buscarCliente(String documento) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getDocumento().equals(documento)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Metodo para Buscar un producto en el menú por su código.
     *
     * @param codigo del producto
     * @return
     */
    public Producto buscarProducto(String codigo) {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca un pedido en el registro por su código identificador.
     *
     * @param codigo Código del pedido.
     * @return
     */
    public Pedido buscarPedido(String codigo) {
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Metodo para Validar, confirma y registra un nuevo pedido en el restaurante.
     *
     * @param nuevoPedido para el restaurante
     * @return
     */
    public boolean registrarPedido(Pedido nuevoPedido) {
        if (nuevoPedido == null) {
            return false;
        }

        if (buscarPedido(nuevoPedido.getCodigo()) != null) {
            return false;
        }

        boolean sePudoConfirmar = nuevoPedido.confirmar();
        if (sePudoConfirmar == false) {
            return false;
        }

        pedidos.add(nuevoPedido);
        nuevoPedido.getCliente().agregarPedido(nuevoPedido);
        return true;
    }

    /**
     *Metodo para Calcular los ingresos propios del restaurante
     *
     * @return
     */
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

    /**
     * Metodo para Calcular el recaudo total
     *
     * @return
     */
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

    /**
     * Metodo para calcular los ingresos globales del restaurante
     *
     * @return
     */
    public double calcularIngresos() {
        return calcularIngresosPropios() + calcularIngresosDomicilios();
    }

    /**
     * Metodo para Generar una lista con los productos del menú que no cumplen con los parámetros de calidad
     *
     * @return
     */
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

    /**
     * Metodo para Determinar el producto que registra la mayor cantidad de unidades vendidas
     *
     * @return
     */
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

    /**
     *Metodo para  Totalizar las ventas monetarias agrupadas por categoría
     *
     * @return
     */
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
