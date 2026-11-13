package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    private static final Logger LOG = Logger.getLogger(RestauranteTest.class.getName());
    private Restaurante restaurante;
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante("El Buen Comer", "900.123.456-7", "Armenia, Quindío");
        cliente = new Cliente("10949001", "Mariana Rios", "3104567890", "mariana@uq.edu.co", "Av. Bolivar");
        restaurante.registrarCliente(cliente);
    }

    @Test
    @DisplayName("Debe registrar un producto exitosamente")
    public void testRegistrarProductoExitoso() {
        LOG.info("Iniciando prueba: testRegistrarProductoExitoso");

        PlatoFuerte plato = new PlatoFuerte("PF01", "Bife de Chorizo", "Corte madurado",
                35000, 30, 1050, TipoPreparacion.ASADO, true);

        boolean registrado = restaurante.registrarProducto(plato);

        assertTrue(registrado);
        assertEquals(1, restaurante.getProductos().size());
        assertEquals("Bife de Chorizo", restaurante.buscarProducto("PF02").getNombre());
    }

    @Test
    @DisplayName("No debe permitir registrar un producto con código duplicado")
    public void testRegistrarProductoDuplicado() {
        LOG.info("Iniciando prueba: testRegistrarProductoDuplicado");

        PlatoFuerte plato1 = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        PlatoFuerte plato2 = new PlatoFuerte("PF01", "Otro Bife", "Carne 2", 32000, 20, 350, TipoPreparacion.ASADO, true);

        assertTrue(restaurante.registrarProducto(plato1));
        assertFalse(restaurante.registrarProducto(plato2));
        assertEquals(1, restaurante.getProductos().size());
    }

    @Test
    @DisplayName("Debe registrar un cliente exitosamente")
    public void testRegistrarClienteExitoso() {
        LOG.info("Iniciando prueba: testRegistrarClienteExitoso");

        Cliente nuevoCliente = new Cliente("10949002", "Juan Gomez", "3119876543", "juan@uq.edu.co", "Cra 14");
        boolean registrado = restaurante.registrarCliente(nuevoCliente);

        assertTrue(registrado);
        assertNotNull(restaurante.buscarCliente("10949002"));
    }

    @Test
    @DisplayName("No debe permitir registrar cliente con documento duplicado")
    public void testRegistrarClienteDuplicado() {
        LOG.info("Iniciando prueba: testRegistrarClienteDuplicado");

        Cliente duplicado = new Cliente("10949001", "Mariana Copia", "3000000000", "copia@uq.edu.co", "Otra Calle");
        boolean registrado = restaurante.registrarCliente(duplicado);

        assertFalse(registrado);
    }

    @Test
    @DisplayName("Debe registrar y confirmar un pedido válido")
    public void testRegistrarPedidoExitoso() {
        LOG.info("Iniciando prueba: testRegistrarPedidoExitoso");

        PlatoFuerte plato = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        restaurante.registrarProducto(plato);

        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 2);

        boolean registrado = restaurante.registrarPedido(pedido);

        assertTrue(registrado);
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());
        assertEquals(1, restaurante.getPedidos().size());
        assertEquals(1, cliente.verHistorial().size());
    }

    @Test
    @DisplayName("No debe registrar pedido con código duplicado")
    public void testRegistrarPedidoDuplicado() {
        LOG.info("Iniciando prueba: testRegistrarPedidoDuplicado");

        PlatoFuerte plato = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        restaurante.registrarProducto(plato);

        Pedido pedido1 = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido1.agregarDetalle(plato, 1);

        Pedido pedido2 = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido2.agregarDetalle(plato, 1);

        assertTrue(restaurante.registrarPedido(pedido1));
        assertFalse(restaurante.registrarPedido(pedido2));
    }

    @Test
    @DisplayName("No debe registrar pedido si algún producto no cumple calidad")
    public void testRegistrarPedidoCalidadInvalida() {
        LOG.info("Iniciando prueba: testRegistrarPedidoCalidadInvalida");

        PlatoFuerte platoMalCocido = new PlatoFuerte("PF02", "Costilla Cruda", "Sin cocción",
                28000, 8, 150, TipoPreparacion.HORNEADO, false);
        restaurante.registrarProducto(platoMalCocido);

        Pedido pedido = new Pedido("PED02", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(platoMalCocido, 1);

        boolean registrado = restaurante.registrarPedido(pedido);

        assertFalse(registrado);
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(0, restaurante.getPedidos().size());
    }

    @Test
    @DisplayName("No debe registrar pedido a domicilio si la distancia supera el límite del servicio")
    public void testRegistrarPedidoDomicilioRechazadoPorDistancia() {
        LOG.info("Iniciando prueba: testRegistrarPedidoDomicilioRechazadoPorDistancia");

        Bebida bebida = new Bebida("BE01", "Jugo", "Frío", 5000, 5, 300, 5.0, TemperaturaServicio.FRIA, false);
        restaurante.registrarProducto(bebida);

        Pedido pedido = new Pedido("PED03", cliente, ModalidadEntrega.DOMICILIO);
        pedido.agregarDetalle(bebida, 1);

        RapidoExpress rapidoExpress = new RapidoExpress(); // Acepta hasta 12 km
        pedido.asignarServicioDomicilio(rapidoExpress, 15.0); // 15 km excede el límite

        boolean registrado = restaurante.registrarPedido(pedido);

        assertFalse(registrado);
        assertFalse(pedido.isEntregaAceptada());
    }

    @Test
    @DisplayName("Debe calcular correctamente los ingresos propios, domicilios y totales")
    public void testCalcularIngresos() {
        LOG.info("Iniciando prueba: testCalcularIngresos");


        PlatoFuerte plato = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        restaurante.registrarProducto(plato);


        Pedido pedido1 = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido1.agregarDetalle(plato, 2);
        restaurante.registrarPedido(pedido1);

        Pedido pedido2 = new Pedido("PED02", cliente, ModalidadEntrega.DOMICILIO);
        pedido2.agregarDetalle(plato, 1);
        RapidoExpress rapido = new RapidoExpress();
        pedido2.asignarServicioDomicilio(rapido, 4.0);
        restaurante.registrarPedido(pedido2);

        assertEquals(108000.0, restaurante.calcularIngresosPropios(), 0.001);
        assertEquals(11000.0, restaurante.calcularIngresosDomicilios(), 0.001);
        assertEquals(119000.0, restaurante.calcularIngresos(), 0.001);
    }

    @Test
    @DisplayName("Debe obtener la lista de productos que no cumplen calidad")
    public void testObtenerProductosNoCumplenCalidad() {
        LOG.info("Iniciando prueba: testObtenerProductosNoCumplenCalidad");

        PlatoFuerte bueno = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        PlatoFuerte malo = new PlatoFuerte("PF02", "Crudo", "Sin cocción", 20000, 5, 100, TipoPreparacion.ASADO, false);

        restaurante.registrarProducto(bueno);
        restaurante.registrarProducto(malo);

        ArrayList<Producto> noCumplen = restaurante.obtenerProductosNoCumplenCalidad();

        assertEquals(1, noCumplen.size());
        assertEquals("PF02", noCumplen.get(0).getCodigo());
    }

    @Test
    @DisplayName("Debe identificar el producto más vendido")
    public void testObtenerProductoMasVendido() {
        LOG.info("Iniciando prueba: testObtenerProductoMasVendido");

        PlatoFuerte plato = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        Postre postre = new Postre("PO01", "Flan", "Dulce", 10000, 15, 200, 4.0, true);

        restaurante.registrarProducto(plato);
        restaurante.registrarProducto(postre);

        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 2);
        pedido.agregarDetalle(postre, 5); // 5 postres vendidos vs 2 platos
        restaurante.registrarPedido(pedido);

        Producto masVendido = restaurante.obtenerProductoMasVendido();

        assertNotNull(masVendido);
        assertEquals("PO01", masVendido.getCodigo());
        assertEquals("Flan", masVendido.getNombre());
    }

    @Test
    @DisplayName("Debe identificar la categoría con mayores ingresos")
    public void testObtenerCategoriaMayorIngreso() {
        LOG.info("Iniciando prueba: testObtenerCategoriaMayorIngreso");


        PlatoFuerte plato = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);

        Postre postre = new Postre("PO01", "Flan", "Dulce", 10000, 15, 200, 4.0, true);

        restaurante.registrarProducto(plato);
        restaurante.registrarProducto(postre);

        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(plato, 3);
        pedido.agregarDetalle(postre, 2);
        restaurante.registrarPedido(pedido);

        String categoriaMayor = restaurante.obtenerCategoriaMayorIngreso();

        assertTrue(categoriaMayor.startsWith("PLATOS_FUERTES"));
    }
}
