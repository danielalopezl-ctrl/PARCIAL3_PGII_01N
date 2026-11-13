package co.edu.uniquindio.poo.model;

import java.util.ArrayList;

/**
 * Clase principal del modelo de negocio que gestiona el restaurante.
 * Administra el catálogo de productos, registro de clientes, procesamiento de pedidos
 * y la generación de reportes comerciales y financieros.
 *
 * @author Julian Gallego
 */
public class Restaurante {

    private String nombreComercial;
    private String nit;
    private String direccion;

    private ArrayList<Producto> productos;
    private ArrayList<Cliente> clientes;
    private ArrayList<Pedido> pedidos;

    /**
     * Constructor principal de Restaurante.
     *
     * @param nombreComercial Razón social o nombre del establecimiento.
     * @param nit Número de identificación tributaria.
     * @param direccion Sede principal del restaurante.
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
     * Registra un nuevo producto en el menú del restaurante.
     * Valida que el producto no sea nulo y que no exista otro con el mismo código.
     *
     * @param nuevoProducto Producto a incorporar al menú.
     * @return boolean true si se registró con éxito; false si es nulo o el código ya existe.
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
     * Registra un nuevo cliente en el sistema.
     * Valida que no exista otro cliente con el mismo documento de identidad.
     *
     * @param nuevoCliente Cliente a registrar.
     * @return boolean true si fue registrado; false si el documento ya existe o es nulo.
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
     * Busca un cliente en el sistema por su número de documento.
     *
     * @param documento Documento de identidad a buscar.
     * @return Cliente Instancia encontrada o null si no existe.
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
     * Busca un producto en el menú por su código.
     *
     * @param codigo Código del producto.
     * @return Producto Instancia encontrada o null si no existe.
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
     * @return Pedido Instancia encontrada o null si no existe.
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
     * Valida, confirma y registra un nuevo pedido en el restaurante.
     * Verifica que el código no exista previamente, ejecuta la confirmación bromatológica
     * y de transporte del pedido, y lo añade tanto al restaurante como al historial del cliente.
     *
     * @param nuevoPedido Pedido a confirmar y registrar.
     * @return boolean true si el pedido fue confirmado y guardado; false en caso contrario.
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
     * Calcula los ingresos propios del restaurante correspondientes únicamente al consumo
     * de comida y bebida de pedidos confirmados o entregados.
     *
     * @return double Total de ingresos por venta de productos.
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
     * Calcula el recaudo total por concepto de servicios de domicilio
     * de pedidos confirmados o entregados.
     *
     * @return double Total de ingresos por transporte.
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
     * Calcula el ingreso global del restaurante sumando los ingresos propios y los de domicilio.
     *
     * @return double Ingreso total.
     */
    public double calcularIngresos() {
        return calcularIngresosPropios() + calcularIngresosDomicilios();
    }

    /**
     * Genera una lista con los productos del menú que no cumplen con los parámetros de calidad bromatológica.
     *
     * @return ArrayList de Producto con productos en estado no conforme.
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
     * Determina el producto que registra la mayor cantidad de unidades vendidas
     * en los pedidos confirmados o entregados.
     *
     * @return Producto con mayor volumen de ventas, o null si no se registran ventas.
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
     * Totaliza las ventas monetarias agrupadas por categoría (PLATOS_FUERTES, BEBIDAS, POSTRES)
     * e identifica cuál representó el mayor ingreso para el restaurante.
     *
     * @return String Nombre de la categoría y su valor facturado.
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

    /**
     * Obtiene la lista completa de productos del menú.
     *
     * @return ArrayList de Producto.
     */
    public ArrayList<Producto> getProductos() {
        return productos;
    }

    /**
     * Obtiene la lista de todos los pedidos registrados en el restaurante.
     *
     * @return ArrayList de Pedido.
     */
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Obtiene el nombre comercial del restaurante.
     *
     * @return String Nombre del restaurante.
     */
    public String getNombreComercial() {
        return nombreComercial;
    }
}
