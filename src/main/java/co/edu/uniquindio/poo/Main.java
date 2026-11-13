package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;


public class Main {

    private static Restaurante restaurante = new Restaurante(
            "El Buen Comer", "900.123.456-7", "Armenia, Quindío"
    );

    private static ServicioEntrega rapidoExpress = new RapidoExpress();
    private static ServicioEntrega entregaSegura = new EntregaSegura();


    public static void main(String[] args) {
        cargarDatosPrueba();

        int opcion = 0;

        do {
            String menu = """
                    SISTEMA DE GESTION - RESTAURANTE EL BUEN COMER

                    1. Registrar producto al menú
                    2. Registrar cliente
                    3. Registrar y confirmar pedido
                    4. Consultar pedido por código
                    5. Consultar historial de pedidos
                    6. Consultar ventas y reportes
                    7. Salir

                    Elija una opción:
                    """;

            try {
                String entrada = JOptionPane.showInputDialog(menu);

                if (entrada == null) {
                    opcion = 7;
                } else {
                    opcion = Integer.parseInt(entrada);
                }

                switch (opcion) {
                    case 1:
                        registrarProductoMenu();
                        break;
                    case 2:
                        registrarCliente();
                        break;
                    case 3:
                        registrarYConfirmarPedido();
                        break;
                    case 4:
                        consultarPedido();
                        break;
                    case 5:
                        consultarHistorialCliente();
                        break;
                    case 6:
                        mostrarReportes();
                        break;
                    case 7:
                        JOptionPane.showMessageDialog(null, "¡Hasta luego!");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida.");
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
            }

        } while (opcion != 7);
    }


    private static void registrarProductoMenu() {

        int tipo = Integer.parseInt(JOptionPane.showInputDialog(
                "Tipo de producto:\n1. Plato Fuerte\n2. Bebida\n3. Postre"
        ));

        String cod = JOptionPane.showInputDialog("Código:");
        String nom = JOptionPane.showInputDialog("Nombre:");
        String desc = JOptionPane.showInputDialog("Descripción:");

        double precio = Double.parseDouble(
                JOptionPane.showInputDialog("Precio base:")
        );

        int tiempo = Integer.parseInt(
                JOptionPane.showInputDialog("Tiempo de preparación (min):")
        );

        Producto p = null;

        if (tipo == 1) {

            double peso = Double.parseDouble(
                    JOptionPane.showInputDialog("Peso (g):")
            );

            int opcPrep = Integer.parseInt(JOptionPane.showInputDialog(
                    "Preparación:\n1. ASADO\n2. FRITO\n3. HORNEADO\n4. A LA PLANCHA"
            ));

            TipoPreparacion prep = TipoPreparacion.values()[opcPrep - 1];

            boolean coccion = JOptionPane.showConfirmDialog(
                    null, "¿Superó control de cocción?"
            ) == JOptionPane.YES_OPTION;

            p = new PlatoFuerte(cod, nom, desc, precio, tiempo,
                    peso, prep, coccion);

        } else if (tipo == 2) {

            int vol = Integer.parseInt(
                    JOptionPane.showInputDialog("Volumen (ml):")
            );

            double temp = Double.parseDouble(
                    JOptionPane.showInputDialog("Temperatura de servicio (°C):")
            );

            int opcTemp = Integer.parseInt(JOptionPane.showInputDialog(
                    "Tipo de temperatura:\n1. FRIA\n2. CALIENTE"
            ));

            TemperaturaServicio tServ = (opcTemp == 1)
                    ? TemperaturaServicio.FRIA
                    : TemperaturaServicio.CALIENTE;

            boolean esp = JOptionPane.showConfirmDialog(
                    null, "¿Requiere preparación especial?"
            ) == JOptionPane.YES_OPTION;

            p = new Bebida(cod, nom, desc, precio, tiempo,
                    vol, temp, tServ, esp);

        } else if (tipo == 3) {

            double peso = Double.parseDouble(
                    JOptionPane.showInputDialog("Peso (g):")
            );

            double temp = Double.parseDouble(
                    JOptionPane.showInputDialog("Temperatura de almacenamiento (°C):")
            );

            boolean ref = JOptionPane.showConfirmDialog(
                    null, "¿Requiere refrigeración?"
            ) == JOptionPane.YES_OPTION;

            p = new Postre(cod, nom, desc, precio, tiempo,
                    peso, temp, ref);
        }

        if (p != null) {
            boolean agregado = restaurante.registrarProducto(p);

            if (agregado) {
                JOptionPane.showMessageDialog(null,
                        "¡Producto registrado con éxito!\nPrecio de venta: $"
                                + p.calcularPrecioVenta());
            } else {
                JOptionPane.showMessageDialog(null,
                        "Error: Código duplicado.");
            }
        }
    }


    private static void registrarCliente() {

        String doc = JOptionPane.showInputDialog("Número de documento:");
        String nom = JOptionPane.showInputDialog("Nombre completo:");
        String tel = JOptionPane.showInputDialog("Teléfono:");
        String correo = JOptionPane.showInputDialog("Correo:");
        String dir = JOptionPane.showInputDialog("Dirección:");

        Cliente c = new Cliente(doc, nom, tel, correo, dir);

        boolean registrado = restaurante.registrarCliente(c);

        if (registrado) {
            JOptionPane.showMessageDialog(null,
                    "¡Cliente registrado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Error: Ya existe un cliente con ese documento.");
        }
    }


    private static void registrarYConfirmarPedido() {

        String codPed = JOptionPane.showInputDialog(
                "Código del nuevo pedido:"
        );

        String doc = JOptionPane.showInputDialog(
                "Documento del cliente:"
        );

        Cliente c = restaurante.buscarCliente(doc);

        if (c == null) {
            JOptionPane.showMessageDialog(null,
                    "Error: El cliente no está registrado.");
            return;
        }

        int modOpc = Integer.parseInt(JOptionPane.showInputDialog(
                "Modalidad:\n1. En Restaurante\n2. Domicilio"
        ));

        ModalidadEntrega modalidad = (modOpc == 1)
                ? ModalidadEntrega.CONSUMO_RESTAURANTE
                : ModalidadEntrega.DOMICILIO;

        Pedido pedido = new Pedido(codPed, c, modalidad);

        boolean seguir = true;

        while (seguir) {

            String productos = "PRODUCTOS DISPONIBLES:\n";

            for (int i = 0; i < restaurante.getProductos().size(); i++) {
                productos += restaurante.getProductos().get(i) + "\n";
            }

            JOptionPane.showMessageDialog(null, productos);

            String codProd = JOptionPane.showInputDialog(
                    "Código del producto a pedir:"
            );

            Producto prod = restaurante.buscarProducto(codProd);

            if (prod == null) {
                JOptionPane.showMessageDialog(null,
                        "Producto no encontrado.");
            } else {

                int cant = Integer.parseInt(
                        JOptionPane.showInputDialog("Cantidad:")
                );

                pedido.agregarDetalle(prod, cant);

                JOptionPane.showMessageDialog(null,
                        "Producto agregado a la orden.");
            }

            seguir = JOptionPane.showConfirmDialog(
                    null, "¿Desea agregar otro producto?"
            ) == JOptionPane.YES_OPTION;
        }

        if (modalidad == ModalidadEntrega.DOMICILIO) {

            int empOpc = Integer.parseInt(JOptionPane.showInputDialog(
                    "Seleccione empresa de transporte:\n"
                            + "1. RápidoExpress (Hasta 12 km)\n"
                            + "2. EntregaSegura (Hasta 20 km)"
            ));

            ServicioEntrega empresa = (empOpc == 1)
                    ? rapidoExpress : entregaSegura;

            double dist = Double.parseDouble(
                    JOptionPane.showInputDialog("Distancia en kilómetros:")
            );

            pedido.asignarServicioDomicilio(empresa, dist);
        }

        boolean confirmado = restaurante.registrarPedido(pedido);

        if (confirmado) {
            JOptionPane.showMessageDialog(null,
                    "¡PEDIDO CONFIRMADO CON ÉXITO!\n\n" + pedido);
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo confirmar el pedido.\n"
                            + "Razones posibles:\n"
                            + "- Algún producto no cumple condiciones de calidad.\n"
                            + "- La empresa de domicilio rechazó la distancia.\n"
                            + "- El código del pedido ya existe.");
        }
    }


    private static void consultarPedido() {

        String cod = JOptionPane.showInputDialog(
                "Ingrese el código del pedido:"
        );

        Pedido p = restaurante.buscarPedido(cod);

        if (p != null) {

            String informacion = "INFORMACIÓN DEL PEDIDO\n\n" + p
                    + "\n\nDETALLE DE PRODUCTOS:\n";

            for (int i = 0; i < p.getDetalles().size(); i++) {

                DetallePedido d = p.getDetalles().get(i);

                informacion += "- " + d.producto().getNombre()
                        + " x" + d.cantidad()
                        + " = $" + d.subtotal() + "\n";
            }

            JOptionPane.showMessageDialog(null, informacion);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Pedido no encontrado.");
        }
    }

    private static void consultarHistorialCliente() {

        String doc = JOptionPane.showInputDialog(
                "Ingrese documento del cliente:"
        );

        Cliente c = restaurante.buscarCliente(doc);

        if (c != null) {

            ArrayList<Pedido> historial = c.verHistorial();

            String informacion = "HISTORIAL DE: "
                    + c.getNombreCompleto() + "\n\n";

            if (historial.isEmpty()) {
                informacion += "El cliente aún no tiene pedidos confirmados.";
            } else {

                for (int i = 0; i < historial.size(); i++) {
                    informacion += historial.get(i) + "\n\n";
                }
            }

            JOptionPane.showMessageDialog(null, informacion);

        } else {
            JOptionPane.showMessageDialog(null,
                    "Cliente no registrado.");
        }
    }

    private static void mostrarReportes() {

        String reporte = "REPORTES DE VENTAS\n\n";

        reporte += "Ingresos por comida y bebida: $"
                + restaurante.calcularIngresosPropios() + "\n";

        reporte += "Ingresos por domicilios: $"
                + restaurante.calcularIngresosDomicilios() + "\n";

        reporte += "Ingreso total restaurante: $"
                + restaurante.calcularIngresos() + "\n";

        Producto masVendido = restaurante.obtenerProductoMasVendido();

        if (masVendido != null) {
            reporte += "\nProducto más vendido: "
                    + masVendido.getNombre();
        } else {
            reporte += "\nProducto más vendido: Sin ventas";
        }

        reporte += "\nCategoría con mayor ingreso: "
                + restaurante.obtenerCategoriaMayorIngreso();

        reporte += "\n\nPRODUCTOS QUE NO CUMPLEN CALIDAD:\n";

        ArrayList<Producto> malos =
                restaurante.obtenerProductosNoCumplenCalidad();

        if (malos.isEmpty()) {
            reporte += "Todos los productos cumplen calidad.";
        } else {
            for (int i = 0; i < malos.size(); i++) {
                reporte += "\n[ALERTA CALIDAD] " + malos.get(i);
            }
        }

        JOptionPane.showMessageDialog(null, reporte);
    }


    private static void cargarDatosPrueba() {

        restaurante.registrarCliente(new Cliente(
                "10949001", "Mariana Rios", "3104567890",
                "mariana@uq.edu.co", "Av. Bolivar"
        ));

        restaurante.registrarCliente(new Cliente(
                "10949002", "Juan Gomez", "3119876543",
                "juan@uq.edu.co", "Cra 14"
        ));

        restaurante.registrarProducto(new PlatoFuerte(
                "PF01", "Bife de Chorizo", "Corte madurado",
                35000, 30, 450, TipoPreparacion.ASADO, true
        ));

        restaurante.registrarProducto(new Bebida(
                "BE01", "Limonada de Coco", "Fría frappe",
                12000, 10, 400, 4.0, TemperaturaServicio.FRIA, true
        ));

        restaurante.registrarProducto(new Postre(
                "PO01", "Cheesecake", "Frutos rojos",
                15000, 15, 180, 4.0, true
        ));

        restaurante.registrarProducto(new PlatoFuerte(
                "PF02", "Costilla Cruda", "Sin cocción",
                28000, 8, 150, TipoPreparacion.HORNEADO, false
        ));
    }
}
