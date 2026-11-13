package co.edu.uniquindio.poo;

import co.edu.uniquindio.poo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    private static final Logger LOG = Logger.getLogger(PedidoTest.class.getName());
    private Cliente cliente;
    private PlatoFuerte platoBueno;
    private PlatoFuerte platoMalo;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("10949001", "Mariana Rios", "3104567890", "mariana@uq.edu.co", "Av. Bolivar");
        platoBueno = new PlatoFuerte("PF01", "Bife", "Carne", 30000, 25, 400, TipoPreparacion.ASADO, true);
        platoMalo = new PlatoFuerte("PF02", "Crudo", "Carne", 30000, 5, 100, TipoPreparacion.ASADO, false);
    }

    @Test
    @DisplayName("DetallePedido: Debe calcular correctamente el subtotal")
    public void testDetallePedidoSubtotal() {
        LOG.info("Iniciando prueba: testDetallePedidoSubtotal");
        // platoBueno precio = 30000 * 1.20 = 36000. 3 unidades = 108000
        DetallePedido detalle = new DetallePedido(platoBueno, 3);
        assertEquals(108000.0, detalle.subtotal(), 0.001);
    }

    @Test
    @DisplayName("Pedido: No cumple condiciones si no tiene productos agregados")
    public void testPedidoVacioNoCumpleCondiciones() {
        LOG.info("Iniciando prueba: testPedidoVacioNoCumpleCondiciones");
        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);

        assertFalse(pedido.cumpleCondiciones());
        assertFalse(pedido.confirmar());
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
    }

    @Test
    @DisplayName("Pedido: No cumple condiciones si algún producto no cumple calidad")
    public void testPedidoConProductoMaloNoCumpleCondiciones() {
        LOG.info("Iniciando prueba: testPedidoConProductoMaloNoCumpleCondiciones");
        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(platoBueno, 1);
        pedido.agregarDetalle(platoMalo, 1);

        assertFalse(pedido.cumpleCondiciones());
        assertFalse(pedido.confirmar());
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
    }

    @Test
    @DisplayName("Pedido Domicilio: Cumple condiciones si la empresa acepta la distancia")
    public void testPedidoDomicilioAceptado() {
        LOG.info("Iniciando prueba: testPedidoDomicilioAceptado");
        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.DOMICILIO);
        pedido.agregarDetalle(platoBueno, 2);

        RapidoExpress rapido = new RapidoExpress();
        pedido.asignarServicioDomicilio(rapido, 8.0); // 8 km es válido para RapidoExpress (máx 12)

        assertTrue(pedido.isEntregaAceptada());
        assertTrue(pedido.cumpleCondiciones());
        assertTrue(pedido.confirmar());
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());


        assertEquals(72000.0, pedido.calcularSubtotalProductos(), 0.001);
        assertEquals(17000.0, pedido.getCostoDomicilio(), 0.001);
        assertEquals(89000.0, pedido.calcularTotal(), 0.001);
    }

    @Test
    @DisplayName("Pedido Domicilio: No cumple condiciones si la empresa rechaza la distancia")
    public void testPedidoDomicilioRechazado() {
        LOG.info("Iniciando prueba: testPedidoDomicilioRechazado");
        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.DOMICILIO);
        pedido.agregarDetalle(platoBueno, 1);

        RapidoExpress rapido = new RapidoExpress();
        pedido.asignarServicioDomicilio(rapido, 15.0); // 15 km excede el límite (máx 12)

        assertFalse(pedido.isEntregaAceptada());
        assertFalse(pedido.cumpleCondiciones());
        assertFalse(pedido.confirmar());
        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
    }

    @Test
    @DisplayName("Pedido: Transiciones de estado (confirmar, entregar, cancelar)")
    public void testTransicionesEstado() {
        LOG.info("Iniciando prueba: testTransicionesEstado");
        Pedido pedido = new Pedido("PED01", cliente, ModalidadEntrega.CONSUMO_RESTAURANTE);
        pedido.agregarDetalle(platoBueno, 1);

        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());

        assertTrue(pedido.confirmar());
        assertEquals(EstadoPedido.CONFIRMADO, pedido.getEstado());

        pedido.marcarEntregado();
        assertEquals(EstadoPedido.ENTREGADO, pedido.getEstado());

        pedido.cancelar();
        assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
    }
}
